package ru.itis.javalab.server.dto.forms;


import lombok.*;
import ru.itis.javalab.server.validation.ValidPasswords;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidPasswords(
        message = "errors.password_edit_form.different_passwords",
        password = "newPassword",
        repeatedPassword = "repeatedNewPassword"
)
public class PasswordEditForm {

    protected String currentPassword;

    @Size(min = 8, max = 40, message = "{errors.password_edit_form.password.bad_size}")
    @NotBlank(message = "{errors.password_edit_form.no_password}")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "{errors.password_edit_form.password.no_letters}")
    @Pattern(regexp = ".*[0-9].*", message = "{errors.password_edit_form.password.no_digits}")
    protected String newPassword;

    @NotBlank(message = "{errors.password_edit_form.no_repeated_password}")
    protected String repeatedNewPassword;

}
