package ru.itis.javalab.server.dto.forms;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignInForm {

    @NotBlank(message = "{errors.sign_in_form.login.no_login}")
    @Pattern(regexp = "[\\w]+", message = "{errors.sign_in_form.login.incorrect_symbols}")
    @Size(min = 3, max = 30, message = "{errors.sign_in_form.login.bad_size}")
    protected String login;

    @Size(min = 8, max = 40, message = "{errors.sign_in_form.password.bad_size}")
    @NotBlank(message = "{errors.sign_in_form.password.no_password}")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "{errors.sign_in_form.password.no_letters}")
    @Pattern(regexp = ".*[0-9].*", message = "{errors.sign_in_form.password.no_digits}")
    protected String password;

    protected String rememberMe;
}
