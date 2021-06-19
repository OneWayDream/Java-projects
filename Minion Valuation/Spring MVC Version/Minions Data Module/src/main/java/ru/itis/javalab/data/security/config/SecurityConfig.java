package ru.itis.javalab.data.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.javalab.data.security.filters.CheckJwtFilter;
import ru.itis.javalab.data.security.filters.JwtLogoutFilter;
import ru.itis.javalab.data.security.filters.TokenAuthenticationFilter;
import ru.itis.javalab.data.security.providers.TokenAuthenticationProvider;

@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected UserDetailsService userDetailsService;

    @Autowired
    protected TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    protected TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    protected CheckJwtFilter checkJwtFilter;

    @Autowired
    protected JwtLogoutFilter jwtLogoutFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(checkJwtFilter, TokenAuthenticationFilter.class);
        http.addFilterBefore(jwtLogoutFilter, TokenAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(tokenAuthenticationProvider);
    }


}
