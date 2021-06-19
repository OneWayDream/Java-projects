package ru.itis.javalab.server.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.javalab.server.filters.FreemarkerAttributesFilter;
import ru.itis.javalab.server.security.filters.GithubAfterAuthorizationFilter;
import ru.itis.javalab.server.security.filters.GithubAuthenticationFilter;
import ru.itis.javalab.server.security.filters.GithubSignRedirectFilter;
import ru.itis.javalab.server.security.providers.GithubAuthenticationProvider;

import javax.sql.DataSource;

@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("common-user-details-service")
    protected UserDetailsService userDetailsService;

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected GithubAuthenticationFilter githubAuthenticationFilter;

    @Autowired
    protected GithubSignRedirectFilter githubSignRedirectFilter;

    @Autowired
    protected GithubAfterAuthorizationFilter githubAfterAuthorizationFilter;

    @Autowired
    protected GithubAuthenticationProvider githubAuthenticationProvider;

    @Autowired
    protected FreemarkerAttributesFilter freemarkerAttributesFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(githubSignRedirectFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(githubAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(githubAfterAuthorizationFilter, AnonymousAuthenticationFilter.class);
        http.addFilterBefore(freemarkerAttributesFilter, GithubSignRedirectFilter.class);
        http
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile")
                .failureUrl("/sign-in?error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(githubAuthenticationProvider);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


}
