package ru.itis.javalab.data.redis.services;

import ru.itis.javalab.data.models.DataAccessUser;

public interface RedisUsersService {

    void addTokenToUser(DataAccessUser user, String token);

    void addAllTokensToBlackList(DataAccessUser user);

}
