package ru.itis.javalab.data.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.exceptions.AuthorizeException;
import ru.itis.javalab.data.exceptions.ExpiredJwtException;
import ru.itis.javalab.data.exceptions.IncorrectJwtException;
import ru.itis.javalab.data.models.AccessToken;
import ru.itis.javalab.data.models.DataAccessUser;
import ru.itis.javalab.data.repositories.RefreshTokensRepository;
import ru.itis.javalab.data.repositories.UsersRepository;

import java.util.Date;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    protected UsersRepository usersRepository;

    @Autowired
    protected RefreshTokensRepository tokensRepository;

    @Value("${jwt.access-secret-key}")
    protected String accessSecretKey;

    @Override
    public UserDetails loadUserByUsername(String tokenValue) throws UsernameNotFoundException {
        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(accessSecretKey))
                    .build()
                    .verify(tokenValue);
//            Date date = java.util.Calendar.getInstance().getTime();
//            Date timeToDie = decodedJWT.getClaim("expiration").asDate();
//            if (date.before(timeToDie)){
                return new UserDetailsImpl(AccessToken.builder()
                        .login(decodedJWT.getClaim("login").asString())
                        .role(decodedJWT.getClaim("role").as(DataAccessUser.Role.class))
                        .state(decodedJWT.getClaim("state").as(DataAccessUser.State.class))
                        .expiration(decodedJWT.getClaim("expiration").asDate())
                        .build());
//            } else {
//                throw new ExpiredJwtException();
//            }
        } catch (JWTVerificationException ex) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new IncorrectJwtException(ex);
        }
    }
}
