package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.DataAccessUser;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<DataAccessUser, Long> {

    Optional<DataAccessUser> findByLogin(String login);

}
