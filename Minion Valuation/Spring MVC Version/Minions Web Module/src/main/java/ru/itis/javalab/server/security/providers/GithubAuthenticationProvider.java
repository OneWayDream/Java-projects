package ru.itis.javalab.server.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.javalab.server.security.authentications.GithubAuthentication;

@Component
public class GithubAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("github-user-details-service")
    protected UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        GithubAuthentication gitHubAuthentication = (GithubAuthentication) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        gitHubAuthentication.setAuthenticated(true);
        gitHubAuthentication.setUserDetails(userDetails);

        return gitHubAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(GithubAuthentication.class);
    }
}
