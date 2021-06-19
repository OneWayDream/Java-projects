package ru.itis.javalab.server.dto.forms;

import lombok.*;
import ru.itis.javalab.server.dto.UserDto;
import ru.itis.javalab.server.models.User;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEditForm {

    @Email(message = "{errors.profile_data_edit_form.incorrect_email}")
    @NotBlank(message = "{errors.profile_data_edit_form.no_email}")
    protected String email;

    @Size(min = 2, max = 30, message = "{errors.profile_data_edit_form.incorrect_first_name_size}")
    protected String firstName;

    @Size(min = 4, max = 30, message = "{errors.profile_data_edit_form.incorrect_minecraft_nickname_size}")
    protected String minecraftNickname;

    @Size(min = 1, max = 30, message = "{errors.profile_data_edit_form.incorrect_country_size}")
    protected String country;

    @Size(max = 30, message = "{errors.profile_data_edit_form.incorrect_vk_size}")
    @Pattern(regexp = "vk.com/[\\w]+", message = "{errors.profile_data_edit_form.incorrect_vk_link}")
    protected String vk;

    @Size(max = 30, message = "{errors.profile_data_edit_form.incorrect_facebook_size}")
    @Pattern(regexp = "facebook.com/[\\w\\\\]+", message = "{errors.profile_data_edit_form.incorrect_facebook_link}")
    protected String facebook;

    @NotNull
    protected User.Gender gender;

    public ProfileEditForm(UserDto userDto){
        this.email = userDto.getEmail();
        this.firstName = userDto.getFirstName();
        this.minecraftNickname = userDto.getMinecraftNickname();
        this.country = userDto.getCountry();
        this.vk = userDto.getVk();
        this.facebook = userDto.getFacebook();
        this.gender = userDto.getGender();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.length()==0 ? null : firstName;
    }

    public void setMinecraftNickname(String minecraftNickname) {
        this.minecraftNickname = minecraftNickname.length()==0 ? null : minecraftNickname;
    }

    public void setCountry(String country) {
        this.country = country.length()==0 ? null : minecraftNickname;
    }

    public void setVk(String vk) {
        this.vk = vk.length()==0 ? null : vk;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook.length()==0 ? null : facebook;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }
}
