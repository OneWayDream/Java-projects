package ru.itis.javalab.data.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.AccessTokenDto;
import ru.itis.javalab.data.dto.LoginPasswordDto;
import ru.itis.javalab.data.dto.RefreshTokenDto;
import ru.itis.javalab.data.exceptions.BannedUserException;
import ru.itis.javalab.data.exceptions.ExpiredJwtException;
import ru.itis.javalab.data.exceptions.IncorrectJwtException;
import ru.itis.javalab.data.exceptions.IncorrectUserDataException;
import ru.itis.javalab.data.models.AccessToken;
import ru.itis.javalab.data.models.DataAccessUser;
import ru.itis.javalab.data.models.RefreshToken;
import ru.itis.javalab.data.redis.services.RedisUsersService;
import ru.itis.javalab.data.repositories.RefreshTokensRepository;
import ru.itis.javalab.data.repositories.UsersRepository;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    protected RefreshTokensRepository refreshTokensRepository;

    @Autowired
    protected UsersRepository usersRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUsersService redisUsersService;

    @Value("${jwt.refresh-secret-key}")
    protected String refreshSecretKey;

    @Value("${jwt.access-secret-key}")
    protected String accessSecretKey;

    @Value("${jwt.refresh-token-lifetime}")
    protected Long refreshTokenLifetime;

    @Value("${jwt.access-token-lifetime}")
    protected Long accessTokenLifetime;

    @Override
    public RefreshTokenDto login(LoginPasswordDto loginPasswordDto) {
        DataAccessUser user = usersRepository.findByLogin(loginPasswordDto.getLogin()).orElseThrow(IncorrectUserDataException::new);
        if (user.getState().equals(DataAccessUser.State.BANNED)){
            throw new BannedUserException();
        }
        if (passwordEncoder.matches(loginPasswordDto.getPassword(), user.getHashPassword())){
            Date date = java.util.Calendar.getInstance().getTime();
            date.setTime(date.getTime() + refreshTokenLifetime);
            String token = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("id", user.getId())
                    .withClaim("login", user.getLogin())
                    .withClaim("state", user.getState().toString())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("expiration", date)
                    .sign(Algorithm.HMAC256(refreshSecretKey));
            RefreshToken refreshToken = RefreshToken.builder()
                    .token(token)
                    .user(user)
                    .build();

            redisUsersService.addTokenToUser(user, token);

            return RefreshTokenDto.from(refreshToken);
        } else {
            throw new IncorrectUserDataException();
        }
    }

    @Override
    public AccessTokenDto authenticate(RefreshTokenDto refreshTokenDto) {
        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(refreshSecretKey))
                    .build()
                    .verify(refreshTokenDto.getToken());
            Date date = java.util.Calendar.getInstance().getTime();
                date.setTime(date.getTime() + accessTokenLifetime);
                String accessToken = JWT.create()
                        .withSubject(decodedJWT.getSubject())
                        .withClaim("login", decodedJWT.getClaim("login").asString())
                        .withClaim("role", decodedJWT.getClaim("role").asString())
                        .withClaim("state", decodedJWT.getClaim("state").asString())
                        .withClaim("expiration", date)
                        .sign(Algorithm.HMAC256(accessSecretKey));

                redisUsersService.addTokenToUser(usersRepository.findByLogin(decodedJWT.getClaim("login").asString())
                        .orElseThrow(IncorrectUserDataException::new), accessToken);

                return AccessTokenDto.builder()
                        .token(accessToken)
                        .build();
        } catch (JWTVerificationException ex) {
            throw new IncorrectJwtException(ex);
        }
    }
}
