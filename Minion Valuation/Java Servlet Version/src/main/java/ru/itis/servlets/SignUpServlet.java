package ru.itis.servlets;

import ru.itis.exceptions.CookiesRepositoryException;
import ru.itis.exceptions.DuplicateUsersException;
import ru.itis.exceptions.UsersRepositoryException;
import ru.itis.models.User;
import ru.itis.services.SecurityService;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

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
        if (req.getSession().getAttribute("user")!=null){
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("login", user.getLogin());
            req.setAttribute("is_signed", true);
        }
        String userBackground = (String) req.getSession().getAttribute("background");
        if (userBackground!=null){
            req.setAttribute("user_background", userBackground);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String repassword = req.getParameter("re_pass");
        String userAccess = req.getParameter("agree-term");
        String userBackground = req.getParameter("user_background");
        req.getSession().setAttribute("background", userBackground);
        User user = User.builder()
                .login(login)
                .email(email)
                .password(password)
                .vk("")
                .role("User")
                .minecraftNickname("")
                .firstName("")
                .country("")
                .facebook("")
                .gender(0)
                .build();
        int checkResult = securityService.verifyUserData(user, repassword, userAccess);
        switch (checkResult){
            case 1: req.setAttribute("message", "You have to fill all form fields.");
                break;
            case 2: req.setAttribute("message", "You have to enter two equals passwords.");
                break;
            case 3: req.setAttribute("message", "You have to access with our rules.");
                break;
            case 4: req.setAttribute("message", "You have to enter E-MAIL in the e-mail field.");
                break;
            case 5: req.setAttribute("message", "Your login must be more than 2 characters long");
                break;
            case 6: req.setAttribute("message", "Your login must be less than 30 characters long");
                break;
            case 7: req.setAttribute("message", "Your email must be less than 30 characters long");
                break;
            case 8: req.setAttribute("message", "Your password must be more than 7 characters long");
                break;
            case 9: req.setAttribute("message", "Your password must be less than 40 characters long");
                break;
            case 10: try{
                user.setPassword(securityService.hashUserPassword(user.getPassword()));
                Long newUserId = usersService.saveUser(user);
                securityService.addRecord(newUserId);
                req.getSession().setAttribute("/success", true);
                resp.sendRedirect(getServletContext().getContextPath()+"/success");
                return;
            } catch (DuplicateUsersException ex){
                req.setAttribute("message", "User with such " +  ex.getMessage() + " already exists.");
            } catch (UsersRepositoryException|CookiesRepositoryException ex){
                req.setAttribute("message", "Something went wrong.");
            }
                break;
        }
        req.setAttribute("user_login", login);
        req.setAttribute("user_email", email);
        req.setAttribute("user_background", userBackground);
        getServletContext().getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
    }
}
