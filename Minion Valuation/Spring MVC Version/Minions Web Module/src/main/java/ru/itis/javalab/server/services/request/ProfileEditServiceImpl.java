package ru.itis.javalab.server.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.server.dto.forms.AccountDeleteForm;
import ru.itis.javalab.server.dto.forms.PasswordEditForm;
import ru.itis.javalab.server.dto.forms.ProfileEditForm;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.exceptions.IncorrectConfirmCodeException;
import ru.itis.javalab.server.exceptions.IncorrectPasswordException;
import ru.itis.javalab.server.services.repositories.UsersService;

@Service
public class ProfileEditServiceImpl implements ProfileEditService {

    @Autowired
    protected UsersService usersService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public UserDto handleProfileEditForm(UserDto userDto, ProfileEditForm profileEditForm) {
        userDto.setEmail(profileEditForm.getEmail());
        userDto.setFirstName(profileEditForm.getFirstName());
        userDto.setMinecraftNickname(profileEditForm.getMinecraftNickname());
        userDto.setCountry(profileEditForm.getCountry());
        userDto.setVk(profileEditForm.getVk());
        userDto.setFacebook(profileEditForm.getFacebook());
        userDto.setGender(profileEditForm.getGender());
        return usersService.update(userDto);
    }

    @Override
    public UserDto handlePasswordEditForm(UserDto userDto, PasswordEditForm passwordEditForm) {
        if (passwordEncoder.matches(passwordEditForm.getCurrentPassword(), userDto.getHashPassword())){
            userDto.setHashPassword(passwordEncoder.encode(passwordEditForm.getNewPassword()));
            return usersService.update(userDto);
        } else {
            throw new IncorrectPasswordException();
        }
    }

    @Override
    public void handleAccountDeleteForm(UserDto userDto, AccountDeleteForm accountDeleteForm) {
        if (accountDeleteForm.getUserConfirm().equals("DELETE_" + userDto.getLogin() + "_ACCOUNT")){
            usersService.delete(userDto);
        } else {
            throw new IncorrectConfirmCodeException();
        }
    }
}
