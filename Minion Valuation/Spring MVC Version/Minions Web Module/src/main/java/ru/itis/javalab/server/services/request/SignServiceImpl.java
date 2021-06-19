package ru.itis.javalab.server.services.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.server.dto.forms.SignUpForm;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.exceptions.*;
import ru.itis.javalab.server.services.repositories.UsersService;
import ru.itis.javalab.server.models.User;
import ru.itis.javalab.server.utils.EmailUtil;
import ru.itis.javalab.server.utils.MailsGenerator;
import java.util.UUID;

@Service
public class SignServiceImpl implements SignService {


    protected UsersService usersService;
    protected MailsGenerator mailsGenerator;
    protected EmailUtil emailUtil;
    protected PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    protected String serverUrl;

    @Value("${email.subject.registration}")
    protected String registrationSubject;

    @Value("${spring.mail.username}")
    protected String from;

    public SignServiceImpl(UsersService usersService, MailsGenerator mailsGenerator,
                           EmailUtil emailUtil, PasswordEncoder passwordEncoder){
        this.usersService = usersService;
        this.mailsGenerator = mailsGenerator;
        this.emailUtil = emailUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void handleSignUpForm(SignUpForm form) {
        UserDto user = UserDto.builder()
                .login(form.getLogin())
                .email(form.getEmail())
                .role(User.Role.USER)
                .gender(User.Gender.MALE)
                .state(User.State.ACTIVE)
                .registrationType(User.RegistrationType.COMMON)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        try{
            user.setHashPassword(passwordEncoder.encode(form.getPassword()));
            usersService.add(user);
            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl ,user.getConfirmCode());
            emailUtil.sendMail(user.getEmail(), registrationSubject, from, confirmMail);
        } catch (DuplicateUsersException ex){
            throw new SignUpException("errors.sign_up_form.user_duplicate");
        } catch (UsersRepositoryException ex){
            throw new SignUpException("errors.sign_up_form.something_wrong");
        }
    }
}
