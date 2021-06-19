package ru.itis.javalab.server.security.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.server.exceptions.UnsuccessfulGithubAuthorizationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GithubAfterAuthorizationFilter extends OncePerRequestFilter {

    @Value("${spring.oauth.github.receiving_url}")
    protected String receivingUrl;

    @Value("${spring.oauth.github.success_url}")
    protected String successUrl;

    private RequestMatcher githubSignInRequest;

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        this.githubSignInRequest = new AntPathRequestMatcher(receivingUrl, "GET");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (githubSignInRequest.matches(request)){
            if (SecurityContextHolder.getContext().getAuthentication()!=null){
                response.sendRedirect(request.getContextPath() + successUrl);
            } else {
                throw new UnsuccessfulGithubAuthorizationException();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
