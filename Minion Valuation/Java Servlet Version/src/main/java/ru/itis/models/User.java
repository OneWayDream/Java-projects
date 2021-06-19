package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {

    protected Long id;
    protected String login;
    protected String email;
    protected String password;
    protected String role;
    protected String firstName;
    protected String minecraftNickname;
    protected Integer gender;
    protected String country;
    protected String vk;
    protected String facebook;

}
