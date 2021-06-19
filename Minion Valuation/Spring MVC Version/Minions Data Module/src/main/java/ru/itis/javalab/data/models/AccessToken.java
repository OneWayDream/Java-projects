package ru.itis.javalab.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "login_index", columnList = "login")
})
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String login;
    protected Date expiration;
    protected DataAccessUser.Role role;
    protected DataAccessUser.State state;

    public boolean isActive() {
        return this.state == DataAccessUser.State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == DataAccessUser.State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == DataAccessUser.Role.ADMIN;
    }

}
