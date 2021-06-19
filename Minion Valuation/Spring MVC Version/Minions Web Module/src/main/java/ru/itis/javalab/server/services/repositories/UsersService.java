package ru.itis.javalab.server.services.repositories;

import ru.itis.javalab.server.dto.UserDto;

public interface UsersService extends CrudService<UserDto, Long>  {

    UserDto findUserByLogin(String login);

}
