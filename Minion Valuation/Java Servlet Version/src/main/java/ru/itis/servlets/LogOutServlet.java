package ru.itis.servlets;

import ru.itis.services.SecurityService;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/log-out")
public class LogOutServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.securityService = (SecurityService) config.getServletContext().getAttribute("securityService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        securityService.logOut(req, resp);
        resp.sendRedirect(getServletContext().getContextPath() + "/main");
    }

}
