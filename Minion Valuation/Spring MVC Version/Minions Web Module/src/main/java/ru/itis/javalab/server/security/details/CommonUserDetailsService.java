package ru.itis.javalab.server.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.models.User;
import ru.itis.javalab.server.repositories.UsersRepository;


@Service("common-user-details-service")
public class CommonUserDetailsService implements UserDetailsService {

    @Autowired
    protected UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = usersRepository
                .findAllByLoginAndRegistrationType(login, User.RegistrationType.COMMON).orElseThrow(
                        () -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(UserDto.from(user));
    }
}
