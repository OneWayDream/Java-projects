package ru.itis.javalab.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.server.models.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = -4089134925307759013L;

    protected Long id;
    protected String login;
    protected String email;
    protected String hashPassword;
    protected String firstName;
    protected String minecraftNickname;
    protected String country;
    protected String vk;
    protected String facebook;
    protected User.State state;
    protected User.Gender gender;
    protected User.Role role;
    protected User.RegistrationType registrationType;
    protected String image;
    protected String confirmCode;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .hashPassword(user.getHashPassword())
                .firstName(user.getFirstName())
                .minecraftNickname(user.getMinecraftNickname())
                .country(user.getCountry())
                .vk(user.getVk())
                .facebook(user.getFacebook())
                .state(user.getState())
                .gender(user.getGender())
                .role(user.getRole())
                .registrationType(user.getRegistrationType())
                .image("Default")
                .confirmCode(user.getConfirmCode())
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

    public boolean isActive(){
        return this.state == User.State.ACTIVE;
    }

    public boolean isBanned(){
        return this.state == User.State.BANNED;
    }

    public boolean isAdmin(){
        return this.role == User.Role.ADMIN;
    }

}
