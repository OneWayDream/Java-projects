package ru.itis.javalab.server.security.details;

import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.exceptions.GithubConnectionException;
import ru.itis.javalab.server.exceptions.GithubDuplicateLoginException;
import ru.itis.javalab.server.models.User;
import ru.itis.javalab.server.repositories.UsersRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service("github-user-details-service")
public class GithubUserDetailsService implements UserDetailsService {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Value("${spring.oauth.github.client_id}")
    protected String clientId;

    @Value("${spring.oauth.github.client_secret}")
    protected String clientSecret;

    protected OkHttpClient client = new OkHttpClient();

    @Autowired
    protected UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String temporaryToken) throws UsernameNotFoundException {
        String accessToken = getAccessToken(temporaryToken);

        String login = getLogin(accessToken);

        String email = getEmail(accessToken);

        Optional<User> userData= usersRepository.findByLoginAndIsDeletedIsNull(login);

        if (userData.isPresent()){
            if (userData.get().getRegistrationType().equals(User.RegistrationType.GITHUB)){
                return new UserDetailsImpl(UserDto.from(userData.get()));
            } else {
                throw new GithubDuplicateLoginException();
            }
        } else {
            User user = User.builder()
                    .login(login)
                    .email(email)
                    .role(User.Role.USER)
                    .gender(User.Gender.MALE)
                    .state(User.State.ACTIVE)
                    .registrationType(User.RegistrationType.GITHUB)
                    .confirmCode(UUID.randomUUID().toString())
                    .build();
            user = usersRepository.save(user);
            return new UserDetailsImpl(UserDto.from(user));
        }
    }

    protected String getAccessToken(String temporaryToken){
        try{
            RequestBody getAccessTokenBody =  new FormEncodingBuilder()
                    .add("client_id", clientId)
                    .add("client_secret", clientSecret)
                    .add("code", temporaryToken)
                    .build();
            Request accessTokenRequest = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(getAccessTokenBody)
                    .build();
            Response response = client.newCall(accessTokenRequest).execute();
            String accessTokenData = response.body().string();
            return accessTokenData.substring(accessTokenData.indexOf('=')+1, accessTokenData.indexOf('&'));
        } catch (Exception ex) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new GithubConnectionException(ex);
        }
    }

    protected String getLogin(String accessToken){
        try{
            RequestBody getUserData = RequestBody.create(JSON, "{\"access_token\":\"" + accessToken + "\"}");
            Request getUserDataRequest = new Request.Builder()
                    .url("https://api.github.com/applications/" + clientId + "/token")
                    .post(getUserData)
                    .header("Accept", "application/vnd.github.v3+json")
                    .header("User-Agent", "App")
                    .header("Authorization", " Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                    .build();
            Response response = client.newCall(getUserDataRequest).execute();
            JSONObject userData = new JSONObject(response.body().string());
            return  ((JSONObject) (userData.get("user"))).getString("login");
        } catch (Exception ex) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new GithubConnectionException(ex);
        }
    }

    protected String getEmail(String accessToken){
        try{
            Request getUserEmailRequest = new Request.Builder()
                    .url("https://api.github.com/user/emails")
                    .header("Accept", "application/json")
                    .header("Accept-Language", "en-us")
                    .header("Authorization", "token " + accessToken)
                    .header("Accept-Encoding", "utf-8")
                    .build();
            Response response = client.newCall(getUserEmailRequest).execute();
            JSONArray userEmail = new JSONArray(response.body().string());
            return userEmail.getJSONObject(0).getString("email");
        } catch (Exception ex) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new GithubConnectionException(ex);
        }
    }
}
