package ru.itis.javalab.server.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDeleteForm {

    @NotBlank(message = "{errors.account_delete_form.blank_confirm_string}")
    protected String userConfirm;

    @AssertTrue(message = "{errors.account_delete_form.not_is_agree}")
    protected Boolean isAgree;

}
