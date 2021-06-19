package ru.itis.repositories;

import ru.itis.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEMail(String email);
    Optional<User> findByLogin(String login);
    void updateUserPassword(User user);
}
