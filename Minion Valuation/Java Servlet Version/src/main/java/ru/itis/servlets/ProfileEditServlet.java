package ru.itis.servlets;

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

@WebServlet("/profile-edit")
public class ProfileEditServlet extends HttpServlet {

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
        setAttributes(req);

        String userBackground = (String) req.getSession().getAttribute("background");
        if (userBackground!=null){
            req.setAttribute("user_background", userBackground);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/profile-edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String form = req.getParameter("form");
        if (form.equals("user_data")){
            String login = req.getParameter("new_login");
            String firstName = req.getParameter("new_first_name");
            String nickname = req.getParameter("new_nickname");
            String email = req.getParameter("new_email");
            String gender = req.getParameter("new_gender");
            String country = req.getParameter("new_country");
            String vk = req.getParameter("new_vk");
            String facebook = req.getParameter("new_facebook");
            String userBackground = req.getParameter("user_background");
            req.getSession().setAttribute("background", userBackground);
            User user = User.builder()
                    .id(((User) req.getSession().getAttribute("user")).getId())
                    .login(login)
                    .firstName(firstName)
                    .minecraftNickname(nickname)
                    .email(email)
                    .country(country)
                    .vk(vk)
                    .facebook(facebook)
                    .build();
            int checkResult = securityService.checkUserChangeData(user, gender);
            switch (checkResult){
                case 1: req.setAttribute("message", "User with such login already exists.");
                    break;
                case 2: req.setAttribute("message", "User with such email already exists.");
                    break;
                case 3: req.setAttribute("message", "Your login must be more than 2 characters long");
                    break;
                case 4: req.setAttribute("message", "Your login must be less than 30 characters long");
                    break;
                case 5: req.setAttribute("message", "Your first name must be more than 1 characters long");
                    break;
                case 6: req.setAttribute("message", "Your first name must be less than 30 characters long");
                    break;
                case 7: req.setAttribute("message", "Your minecraft nickname must be more than 3 characters long");
                    break;
                case 8: req.setAttribute("message", "Your minecraft nickname must be less than 30 characters long");
                    break;
                case 9: req.setAttribute("message", "You have to enter E-MAIL in the e-mail field.");
                    break;
                case 10: req.setAttribute("message", "Your email must be less than 30 characters long");
                    break;
                case 11: req.setAttribute("message", "Your country must be less than 30 characters long");
                    break;
                case 12: req.setAttribute("message", "You have to enter VK-link in the VK field.");
                    break;
                case 13: req.setAttribute("message", "Your vk-link must be less than 30 characters long");
                    break;
                case 14: req.setAttribute("message", "You have to enter Facebook-link in the Facebook field.");
                    break;
                case 15: req.setAttribute("message", "Your facebook-link must be less than 30 characters long");
                    break;
                case 16: try{
                    usersService.updateUser(user, req);
                    req.getSession().setAttribute("message", "Your data has been successfully changed!");
                    resp.sendRedirect(getServletContext().getContextPath()+"/profile");
                    return;
                } catch (UsersRepositoryException ex){
                    req.setAttribute("message", "Something went wrong.");
                }
                    break;
            }
            req.setAttribute("login", user.getLogin());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("first_name", firstName);
            req.setAttribute("nickname", nickname);
            req.setAttribute("gender", gender);
            req.setAttribute("country", country);
            req.setAttribute("vk", vk);
            req.setAttribute("facebook", facebook);
            req.setAttribute("is_signed", true);
            req.setAttribute("image", "Default");
            req.setAttribute("user_background", userBackground);

            getServletContext().getRequestDispatcher("/WEB-INF/views/profile-edit.jsp").forward(req,resp);
        } else if (form.equals("change_password")){
            User user = (User) req.getSession().getAttribute("user");
            String password = req.getParameter("current_password");
            String newPassword = req.getParameter("new_password");
            String reNewPassword = req.getParameter("re_new_password");
            String userBackground = req.getParameter("user_background");
            req.getSession().setAttribute("background", userBackground);
            boolean isCorrect = securityService.matches(password, user.getPassword());
            if (isCorrect){
                if (newPassword.equals(reNewPassword)){
                    user.setPassword(securityService.hashUserPassword(newPassword));
                    try{
                        usersService.updateUserPassword(user, req);
                        req.getSession().setAttribute("message", "Your password has been successfully changed!");
                        resp.sendRedirect(getServletContext().getContextPath()+"/profile");
                        return;
                    } catch (UsersRepositoryException ex){
                        req.setAttribute("password_message", "Something went wrong.");
                    }
                } else {
                    req.setAttribute("password_message", "You must enter two identical new passwords");
                }
            } else {
                req.setAttribute("password_message", "An incorrect account password was entered");
            }
            setAttributes(req);
            req.setAttribute("user_background", userBackground);
            getServletContext().getRequestDispatcher("/WEB-INF/views/profile-edit.jsp").forward(req,resp);
        } else if (form.equals("delete_account")){
            User user = (User) req.getSession().getAttribute("user");
            String password = req.getParameter("delete_password");
            String userAccess = req.getParameter("delete_access");
            String userBackground = req.getParameter("user_background");
            req.getSession().setAttribute("background", userBackground);
            boolean isCorrect = securityService.matches(password, user.getPassword());
            if (isCorrect){
                if (!userAccess.equals("")){
                    try{
                        securityService.deleteCookieForUser(user, req, resp);
                        usersService.deleteUser(user, req);
                        resp.sendRedirect(getServletContext().getContextPath()+"/main");
                        return;
                        } catch (UsersRepositoryException ex){
                            req.setAttribute("delete_message", "Something went wrong.");
                        }
                } else {
                    req.setAttribute("delete_message", "You must confirm that you want to delete your account");
                }
            } else {
                req.setAttribute("delete_message", "An incorrect account password was entered");
            }
            setAttributes(req);
            req.setAttribute("user_background", userBackground);
            getServletContext().getRequestDispatcher("/WEB-INF/views/profile-edit.jsp").forward(req,resp);
        } else {
            resp.sendRedirect(getServletContext().getContextPath()+"/main");
        }
    }

    protected void setAttributes(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("login", user.getLogin());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("is_signed", true);
        String role = (user.getRole().equals(""))? "" : user.getRole();
        String firstName = (user.getFirstName().equals(""))? "" : user.getFirstName();
        String nickname = (user.getMinecraftNickname().equals(""))? "" : user.getMinecraftNickname();
        Integer genderData = user.getGender();
        String gender;
        switch (genderData){
            case 1: gender = "Male"; break;
            case 2: gender = "Female"; break;
            default: gender = "Attack Helicopter";
        }
        String country = (user.getCountry().equals(""))? "" : user.getCountry();
        String vk = (user.getVk().equals(""))? "" : user.getVk();
        String facebook = (user.getFacebook().equals(""))? "" : user.getFacebook();
        req.setAttribute("role", role);
        req.setAttribute("first_name", firstName);
        req.setAttribute("nickname", nickname);
        req.setAttribute("gender", gender);
        req.setAttribute("country", country);
        req.setAttribute("vk", vk);
        req.setAttribute("facebook", facebook);

        req.setAttribute("image", "Default");
    }

}
