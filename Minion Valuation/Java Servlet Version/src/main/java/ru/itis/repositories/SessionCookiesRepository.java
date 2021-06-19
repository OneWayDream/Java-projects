package ru.itis.repositories;

import ru.itis.models.SessionCookie;
import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface SessionCookiesRepository extends CrudRepository<SessionCookie> {
    Optional<SessionCookie> findByCookie(String sessionId);
    Optional<SessionCookie> findEntryByUserId(Long user_id);
    void deleteCookieForUser(User user);
}
