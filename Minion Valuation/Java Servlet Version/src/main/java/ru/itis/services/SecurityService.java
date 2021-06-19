package ru.itis.services;

import ru.itis.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    int verifyUserData(User user, String repeatedPassword, String userAccess);
    boolean isSigned(HttpServletRequest req);
    Cookie signIn(Long id);
    void addRecord(Long user_id);
    Long findUserIdByCookie(String session_id);
    String hashUserPassword(String password);
    boolean matches(String password, String hash);
    void logOut( HttpServletRequest request, HttpServletResponse response);
    int checkUserChangeData(User user, String gender);
    void deleteCookieForUser(User user, HttpServletRequest request, HttpServletResponse response);

}
