package ru.itis.javalab.data.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.javalab.data.models.AccessToken;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    protected AccessToken accessToken;

    public UserDetailsImpl(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken(){
        return accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(accessToken.getRole().toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return accessToken.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return java.util.Calendar.getInstance().getTime().before(accessToken.getExpiration());
    }

    @Override
    public boolean isAccountNonLocked() {
        return accessToken.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accessToken.isActive();
    }
}
