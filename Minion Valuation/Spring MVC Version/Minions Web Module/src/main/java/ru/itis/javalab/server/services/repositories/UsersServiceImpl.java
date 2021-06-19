package ru.itis.javalab.server.services.repositories;

import org.springframework.stereotype.Service;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.exceptions.DuplicateUsersException;
import ru.itis.javalab.server.exceptions.UserNotExistsException;
import ru.itis.javalab.server.exceptions.UserNotFoundException;
import ru.itis.javalab.server.exceptions.UsersRepositoryException;
import ru.itis.javalab.server.models.User;
import ru.itis.javalab.server.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    protected UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> findAll() {
        try{
            return UserDto.from(usersRepository.findAllByIsDeletedIsNull());
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public UserDto findUserByLogin(String login) {
        try{
            return UserDto.from(usersRepository.findByLogin(login).orElseThrow(UserNotFoundException::new));
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public UserDto findById(Long id) {
        try{
            return UserDto.from(usersRepository.findById(id).orElseThrow(UserNotFoundException::new));
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public UserDto update(UserDto userDto) {
        try{
            User userForUpdate = usersRepository.findByLogin(userDto.getLogin())
                    .filter(user -> user.getIsDeleted()==null)
                    .orElseThrow(UserNotExistsException::new);
            userForUpdate.setEmail(userDto.getEmail());
            userForUpdate.setHashPassword(userDto.getHashPassword());
            userForUpdate.setFirstName(userDto.getFirstName());
            userForUpdate.setMinecraftNickname(userDto.getMinecraftNickname());
            userForUpdate.setCountry(userDto.getCountry());
            userForUpdate.setVk(userDto.getVk());
            userForUpdate.setFacebook(userDto.getFacebook());
            userForUpdate.setState(userDto.getState());
            userForUpdate.setGender(userDto.getGender());
            userForUpdate.setRole(userDto.getRole());
            userDto.setRegistrationType(userDto.getRegistrationType());
            userDto.setConfirmCode(userDto.getConfirmCode());
            usersRepository.save(userForUpdate);
            return UserDto.from(userForUpdate);
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public void delete(UserDto userDto) {
        try{
            User userForDelete = usersRepository.findByLogin(userDto.getLogin())
                    .filter(item -> item.getIsDeleted()==null)
                    .orElseThrow(UserNotExistsException::new);
            userForDelete.setIsDeleted(true);
            usersRepository.save(userForDelete);
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }

    @Override
    public UserDto add(UserDto userDto){
        try{
            Optional<User> checkUnique = usersRepository
                    .findByLogin(userDto.getLogin());
            if (checkUnique.isPresent()){
                throw new DuplicateUsersException();
            }
            User newUser = User.builder()
                    .login(userDto.getLogin())
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstName())
                    .minecraftNickname(userDto.getMinecraftNickname())
                    .country(userDto.getCountry())
                    .vk(userDto.getVk())
                    .facebook(userDto.getFacebook())
                    .state(userDto.getState())
                    .gender(userDto.getGender())
                    .role(userDto.getRole())
                    .registrationType(userDto.getRegistrationType())
                    .confirmCode(userDto.getConfirmCode())
                    .hashPassword(userDto.getHashPassword())
                    .build();
            usersRepository.save(newUser);
            return UserDto.from(newUser);
        } catch (DuplicateUsersException ex){
            throw new DuplicateUsersException();
        } catch (Exception ex){
            throw new UsersRepositoryException(ex);
        }
    }
}
