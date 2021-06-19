package ru.itis.servlets;

import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("login", user.getLogin());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("is_signed", true);
        String role = (user.getRole().equals(""))? "Undefined" : user.getRole();
        String firstName = (user.getFirstName().equals(""))? "Undefined" : user.getFirstName();
        String nickname = (user.getMinecraftNickname().equals(""))? "Undefined" : user.getMinecraftNickname();
        Integer genderData = user.getGender();
        String gender;
        switch (genderData){
            case 1: gender = "Male"; break;
            case 2: gender = "Female"; break;
            default: gender = "Attack Helicopter";
        }
        String country = (user.getCountry().equals(""))? "Undefined" : user.getCountry();
        String vk = (user.getVk().equals(""))? "Undefined" : user.getVk();
        String facebook = (user.getFacebook().equals(""))? "Undefined" : user.getFacebook();
        req.setAttribute("role", role);
        if (role.equals("User")){
            req.setAttribute("text_color", "text-success");
        } else if (role.equals("Admin")){
            req.setAttribute("text_color", "text-danger");
        }
        req.setAttribute("first_name", firstName);
        req.setAttribute("nickname", nickname);
        req.setAttribute("gender", gender);
        req.setAttribute("country", country);
        req.setAttribute("vk", vk);
        req.setAttribute("facebook", facebook);

        req.setAttribute("image", "Default");

        String userBackground = (String) req.getSession().getAttribute("background");
        if (userBackground!=null){
            req.setAttribute("user_background", userBackground);
        }

        String message = (String) req.getSession().getAttribute("message");
        if (message!=null){
            req.getSession().setAttribute("message", null);
            req.setAttribute("message", message);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
    }
}
