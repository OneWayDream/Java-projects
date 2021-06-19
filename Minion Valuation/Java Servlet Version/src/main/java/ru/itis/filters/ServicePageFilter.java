package ru.itis.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class ServicePageFilter extends HttpFilter {

    protected final String[] serviceUrls = {"/success", "/exception"};

    private static final Logger logger = LoggerFactory.getLogger(
            ServicePageFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean isService = false;
        for(String path : serviceUrls){
            if(path.equals(req.getRequestURI().substring(req.getContextPath().length()))){
                if (req.getSession().getAttribute(path)==null){
                    logger.info("User " + req.getRemoteAddr() + " tried to enter the service url - " + path);
                    res.sendRedirect(req.getContextPath() + "/main");
                } else {
                    req.getSession().setAttribute(path, null);
                    chain.doFilter(req, res);
                }
                isService = true;
            }
        }
        if (!isService){
            chain.doFilter(req, res);
        }
    }
}
