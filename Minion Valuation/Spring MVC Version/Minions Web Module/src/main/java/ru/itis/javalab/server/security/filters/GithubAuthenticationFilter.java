package ru.itis.javalab.server.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.server.security.authentications.GithubAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GithubAuthenticationFilter extends OncePerRequestFilter {

    @Value("${spring.oauth.github.receiving_url}")
    protected String receivingUrl;

    @Value("${spring.oauth.github.success_url}")
    protected String successUrl;

    @Autowired
    protected AuthenticationManager authenticationManager;

    private RequestMatcher githubSignInRequest;

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        this.githubSignInRequest = new AntPathRequestMatcher(receivingUrl, "GET");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (githubSignInRequest.matches(request)){
            GithubAuthentication githubAuthentication = new GithubAuthentication(request.getParameter("code"));
            authenticationManager.authenticate(githubAuthentication);
            SecurityContextHolder.getContext().setAuthentication(githubAuthentication);
        }
        filterChain.doFilter(request, response);
    }

}
