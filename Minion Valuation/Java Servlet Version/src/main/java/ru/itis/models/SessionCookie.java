package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SessionCookie {

    protected Long id;
    protected Long userId;
    protected String sessionId;

}
