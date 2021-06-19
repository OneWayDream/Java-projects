package ru.itis.javalab.server.dto.forms;

import lombok.Data;
import ru.itis.javalab.server.validation.ValidPasswords;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ValidPasswords(
        message = "{errors.sign_up_form.password.different_passwords}",
        password = "password",
        repeatedPassword = "repeatedPassword"
)
public class SignUpForm implements Serializable {


    @NotBlank(message = "{errors.sign_up_form.login.no_login}")
    @Pattern(regexp = "[\\w]+", message = "{errors.sign_up_form.login.incorrect_symbols}")
    @Size(min = 3, max = 30, message = "{errors.sign_up_form.login.bad_size}")
    protected String login;

    @Email(message = "{errors.sign_up_form.incorrect_email}")
    @NotBlank(message = "{errors.sign_up_form.email.no_email}")
    protected String email;

    @Size(min = 8, max = 40, message = "{errors.sign_up_form.password.bad_size}")
    @NotBlank(message = "{errors.sign_up_form.password.no_password}")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "{errors.sign_up_form.password.no_letters}")
    @Pattern(regexp = ".*[0-9].*", message = "{errors.sign_up_form.password.no_digits}")
    protected String password;

    @NotBlank(message = "{errors.sign_up_form.password.no_repeated_password}")
    protected String repeatedPassword;

    @NotBlank(message = "{errors.sign_up_form.user_access.no_user_access}")
    protected String userAccess;

}
