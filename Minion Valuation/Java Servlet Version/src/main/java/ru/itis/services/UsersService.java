package ru.itis.services;

import ru.itis.exceptions.DuplicateUsersException;
import ru.itis.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UsersService  {

    List<User> getAllUsers();
    Long saveUser(User user) throws DuplicateUsersException;
    Optional<User> findUserByEMail(String eMail);
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
    void updateUser (User user, HttpServletRequest request);
    void updateUserPassword (User user, HttpServletRequest request);
    void deleteUser (User user, HttpServletRequest request);


}
