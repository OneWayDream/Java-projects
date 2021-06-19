package ru.itis.javalab.server.security.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GithubSignRedirectFilter extends OncePerRequestFilter {

    @Value("${spring.oauth.github.redirect_url}")
    protected String redirectUrl;

    @Value("${spring.oauth.github.client_id}")
    protected String clientId;

    @Value("${spring.oauth.github.github_redirect_url}")
    protected String githubRedirectUrl;

    private RequestMatcher githubSignInRedirect;

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        githubSignInRedirect = new AntPathRequestMatcher(redirectUrl, "GET");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (githubSignInRedirect.matches(request)){
            String redirectedGitPath = "https://github.com/login/oauth/authorize?client_id=" + clientId
                    + "&redirect_uri=" + githubRedirectUrl + "&scope=user";
            response.sendRedirect(redirectedGitPath);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
