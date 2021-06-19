package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.models.DataAccessUser;
import ru.itis.javalab.data.redis.services.RedisUsersService;
import ru.itis.javalab.data.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisUsersService redisUsersService;

    @Override
    public void blockUser(Long userId) {
        DataAccessUser user = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        redisUsersService.addAllTokensToBlackList(user);
        user.setState(DataAccessUser.State.BANNED);
        usersRepository.save(user);
    }
}
