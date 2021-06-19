package ru.itis.javalab.server.security.authentications;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.javalab.server.security.details.UserDetailsImpl;

import java.util.Collection;

public class GithubAuthentication implements Authentication {

    protected UserDetailsImpl userDetails;
    protected Boolean isAuthenticated;
    protected String temporaryToken;

    public GithubAuthentication(String temporaryToken){
        this.temporaryToken = temporaryToken;
        this.isAuthenticated = false;
    }

    public void setUserDetails(UserDetails userDetails){
        this.userDetails = (UserDetailsImpl) userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
//        return (userDetails != null) ? userDetails.getUser() : null;
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return temporaryToken;
    }
}
