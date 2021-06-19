package ru.itis.servlets;

import ru.itis.exceptions.CookiesRepositoryException;
import ru.itis.exceptions.UsersRepositoryException;
import ru.itis.models.User;
import ru.itis.services.SecurityService;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private UsersService usersService;
    private SecurityService securityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        this.securityService = (SecurityService) config.getServletContext().getAttribute("securityService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("signInMessage")!=null){
            req.setAttribute("message", req.getSession().getAttribute("signInMessage"));
            req.getSession().setAttribute("signInMessage", null);
        }
        if (req.getSession().getAttribute("user")!=null){
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("login", user.getLogin());
            req.setAttribute("is_signed", true);
        }
        String userBackground = (String) req.getSession().getAttribute("background");
        if (userBackground!=null){
            req.setAttribute("user_background", userBackground);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/sign-in.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("your_pass");
        String userAccess = req.getParameter("remember-me");
        String userBackground = req.getParameter("user_background");
        req.getSession().setAttribute("background", userBackground);
        System.out.println(userBackground);
        try{
            Optional<User> user = usersService.findUserByLogin(login);
            boolean isUser = false;
            if (user.isPresent()){
                isUser = securityService.matches(password, user.get().getPassword().trim());
            }
            if (isUser){
                req.getSession().setAttribute("user", user.get());
                Cookie cookie = securityService.signIn(user.get().getId());
                if (userAccess!=null){
                    cookie.setMaxAge(60 * 60 * 12);
                }
                resp.addCookie(cookie);
                resp.sendRedirect(getServletContext().getContextPath()+"/profile");
            } else {
                req.setAttribute("user_background", userBackground);
                req.setAttribute("user_login", login);
                if (user.isPresent()){
                    req.setAttribute("message", "Wrong password entered");
                } else {
                    req.setAttribute("message", "There is no user with this name.");
                }
                getServletContext().getRequestDispatcher("/WEB-INF/views/sign-in.jsp").forward(req, resp);
            }
        } catch (UsersRepositoryException|CookiesRepositoryException ex) {
            req.setAttribute("user_background", userBackground);
            req.setAttribute("user_login", login);
            req.setAttribute("message", "Something went wrong.");
            getServletContext().getRequestDispatcher("/WEB-INF/views/sign-in.jsp").forward(req, resp);
        }
    }
}
