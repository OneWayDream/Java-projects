package ru.itis.javalab.data.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.data.exceptions.ExpiredJwtException;
import ru.itis.javalab.data.services.JwtBlacklistService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Component
public class CheckJwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtBlacklistService service;

    @Value("${jwt.access-secret-key}")
    protected String accessSecretKey;

    @Value("${jwt.refresh-secret-key}")
    protected String refreshSecretKey;

    protected RequestMatcher requestMatcher = new AntPathRequestMatcher("/auth", "POST");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = (request.getHeader("JWT")==null) ?
                request.getHeader("JWT-refresh") : request.getHeader("JWT");

        if (token!=null){

            if (service.exists(token)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            DecodedJWT decodedJWT = (requestMatcher.matches(request))
                    ? JWT.require(Algorithm.HMAC256(refreshSecretKey))
                    .build()
                    .verify(token)
                    : JWT.require(Algorithm.HMAC256(accessSecretKey))
                    .build()
                    .verify(token);

            Date date = java.util.Calendar.getInstance().getTime();
            Date timeToDie = decodedJWT.getClaim("expiration").asDate();
            if (!date.before(timeToDie)){
                throw new ExpiredJwtException();
            }
        }

//        if (requestMatcher.matches(request)){
//
//
//            System.out.println(request.getParameterMap().size());
//
//            if (service.exists((String) request.getParameter("token"))) {
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }
//
//            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(refreshSecretKey))
//                    .build()
//                    .verify((String) request.getAttribute("token"));
//            Date date = java.util.Calendar.getInstance().getTime();
//            Date timeToDie = decodedJWT.getClaim("expiration").asDate();
//            if (!date.before(timeToDie)){
//                throw new ExpiredJwtException();
//            }
//        }

        chain.doFilter(request, response);

    }

}
