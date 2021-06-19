package ru.itis.javalab.server.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users",
        indexes = {
        @Index(name = "login_index", columnList = "login"),
        @Index(name = "id_index", columnList = "id", unique = true)
        })
public class User implements Serializable {

    private static final long serialVersionUID = 4710201667564692049L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String login;
    protected String email;
    protected String hashPassword;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "nickname")
    protected String minecraftNickname;


    protected String country;
    protected String vk;
    protected String facebook;

    @Column(name = "confirm_code")
    protected String confirmCode;

    @Enumerated(value = EnumType.STRING)
    protected State state;

    public enum State {
        ACTIVE, NOT_CONFIRMED, BANNED
    }

    @Enumerated(value = EnumType.STRING)
    protected Gender gender;

    public enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        ATTACK_HELICOPTER("Attack Helicopter");

        public String value;

        Gender(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }

        public static Gender byValue(String value){
            for (Gender g : values()){
                if (g.getValue().equals(value)){
                    return g;
                }
            }
            return null;
        }
    }

    @Enumerated(value = EnumType.STRING)
    protected Role role;

    public enum Role{
        USER, ADMIN, MODERATOR, TESTER
    }

    @Enumerated(value = EnumType.STRING)
    protected RegistrationType registrationType;

    public enum RegistrationType{
        COMMON, GITHUB
    }

    protected Boolean isDeleted;
}
