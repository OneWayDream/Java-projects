package ru.itis.javalab.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.server.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsDeletedIsNull();

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndIsDeletedIsNull(String login);

    Optional<User> findAllByLoginAndRegistrationType(String login, User.RegistrationType registrationType);

}
