package ru.itis.javalab.data.security.authentications;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.javalab.data.security.details.UserDetailsImpl;

import java.util.Collection;


public class TokenAuthentication implements Authentication {

    protected UserDetailsImpl userDetails;
    protected Boolean isAuthenticated;
    protected String token;

    public TokenAuthentication(String token){
        this.token = token;
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
        return (userDetails != null) ? userDetails.getAccessToken() : null;
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
        return token;
    }
}
