package ru.itis.javalab.server.services.request;

import ru.itis.javalab.server.dto.forms.AccountDeleteForm;
import ru.itis.javalab.server.dto.forms.PasswordEditForm;
import ru.itis.javalab.server.dto.forms.ProfileEditForm;
import ru.itis.javalab.server.dto.UserDto;

public interface ProfileEditService {
    UserDto handleProfileEditForm(UserDto userDto, ProfileEditForm profileEditForm);
    UserDto handlePasswordEditForm(UserDto userDto, PasswordEditForm passwordEditForm);
    void handleAccountDeleteForm(UserDto userDto, AccountDeleteForm accountDeleteForm);
}
