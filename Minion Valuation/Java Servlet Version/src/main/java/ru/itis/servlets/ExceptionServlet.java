package ru.itis.servlets;

import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exception")
public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookieErrorMessage = (String) req.getSession().getAttribute("cookieErrorMessage");
        req.getSession().setAttribute("cookieErrorMessage", null);
        String unexpectedExceptionMessage = (String) req.getSession().getAttribute("unexpectedExceptionMessage");
        req.getSession().setAttribute("unexpectedExceptionMessage", null);
        if (cookieErrorMessage!=null){
            req.setAttribute("message", cookieErrorMessage);
        }
        if (unexpectedExceptionMessage!=null){
            req.setAttribute("message", unexpectedExceptionMessage);
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
        getServletContext().getRequestDispatcher("/WEB-INF/views/exception.jsp").forward(req,resp);
    }

}
