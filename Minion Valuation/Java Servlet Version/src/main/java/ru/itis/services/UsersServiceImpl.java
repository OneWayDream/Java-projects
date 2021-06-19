package ru.itis.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.exceptions.DuplicateUsersException;
import ru.itis.exceptions.UsersRepositoryException;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    protected UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(
            UsersServiceImpl.class);

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEMail(String eMail) {
        try {
            return usersRepository.findByEMail(eMail);
        } catch (IllegalStateException ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            return usersRepository.findByLogin(login);
        } catch (IllegalStateException ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public void updateUser(User user, HttpServletRequest request) {
        User databaseUser = (User) request.getSession().getAttribute("user");
        user.setId(databaseUser.getId());
        user.setPassword(databaseUser.getPassword());
        user.setRole(databaseUser.getRole());
        try{
            usersRepository.update(user);
            request.getSession().setAttribute("user", user);
            logger.info("User " + user.getId() + " was successfully updated.");
        } catch (IllegalStateException ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public void updateUserPassword(User user, HttpServletRequest request) {
        usersRepository.updateUserPassword(user);
        request.getSession().setAttribute("user", user);
        logger.info("Password for user " + user.getId() + " was successfully updated.");
    }

    @Override
    public void deleteUser(User user, HttpServletRequest request) {
        try{
            usersRepository.delete(user);
            logger.info("User " + user.getId() + " was successfully deleted.");
        } catch (IllegalStateException ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public Long saveUser(User user) throws DuplicateUsersException {
        Optional<User> existUser = this.findUserByEMail(user.getEmail());
        if (existUser.isPresent()){
            throw new DuplicateUsersException("email");
        } else {
            existUser = this.findUserByLogin(user.getLogin());
            if (existUser.isPresent()){
                throw new DuplicateUsersException("login");
            } else {
                try{
                    usersRepository.save(user);
                    logger.info("User " + user.getLogin() + " - " + user.getEmail() + " " + " was successfully deleted.");
                    return usersRepository.findByEMail(user.getEmail()).get().getId();
                } catch (IllegalStateException ex){
                    throw new UsersRepositoryException(ex);
                }
            }
        }
    }
}
