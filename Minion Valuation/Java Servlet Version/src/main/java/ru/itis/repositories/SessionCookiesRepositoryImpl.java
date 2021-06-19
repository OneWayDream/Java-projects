package ru.itis.repositories;

import ru.itis.models.SessionCookie;
import ru.itis.models.User;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SessionCookiesRepositoryImpl implements SessionCookiesRepository {


    //language=SQL
    private static final String SQL_FIND_BY_COOKIE = "select * from cookies where session_id::text=?";
    //language=SQL
    private static final String SQL_UPDATE_COOKIE_FOR_USER = "update cookies set session_id=?::uuid where user_id=?";
    //language=SQL
    private static final String SQL_FIND_BY_USER_ID = "select * from cookies where user_id=?";
    //language=SQL
    private static final String SQL_INSERT_NEW_COOKIE = "insert into cookies(user_id, session_id) values (?, ?::uuid)";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM cookies WHERE id=?";
    //language=SQL
    private static final String SQL_DELETE_FOR_USER = "DELETE FROM cookies WHERE user_id=?";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM cookies WHERE id=?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM cookies";

    private SimpleJDBSTemplate simpleJDBSTemplate;
    private DataSource dataSource;

    private RowMapper<SessionCookie> sessionCookieRowMapper = row -> SessionCookie.builder()
            .id(row.getLong("id"))
            .userId(row.getLong("user_id"))
            .sessionId(row.getString("session_id").trim())
            .build();

    public SessionCookiesRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJDBSTemplate = new SimpleJDBSTemplate(dataSource);
    }

    @Override
    public Optional<SessionCookie> findByCookie(String sessionId) {
        return simpleJDBSTemplate.query(SQL_FIND_BY_COOKIE, sessionCookieRowMapper, sessionId).stream().findAny();
    }

    @Override
    public void save(SessionCookie entity) {
        simpleJDBSTemplate.query(SQL_INSERT_NEW_COOKIE, sessionCookieRowMapper, entity.getUserId(), entity.getSessionId());
    }

    @Override
    public void update(SessionCookie entity) {
        simpleJDBSTemplate.query(SQL_UPDATE_COOKIE_FOR_USER, sessionCookieRowMapper, entity.getSessionId(), entity.getUserId());
    }

    @Override
    public Optional<SessionCookie> findEntryByUserId(Long user_id) {
        return simpleJDBSTemplate.query(SQL_FIND_BY_USER_ID, sessionCookieRowMapper, user_id).stream().findAny();
    }

    @Override
    public void deleteCookieForUser(User user) {
        simpleJDBSTemplate.query(SQL_DELETE_FOR_USER, sessionCookieRowMapper, user.getId());
    }

    @Override
    public void delete(SessionCookie entity) {
        simpleJDBSTemplate.query(SQL_DELETE, sessionCookieRowMapper, entity.getId());
    }

    @Override
    public Optional<SessionCookie> findById(Long id) {
        return simpleJDBSTemplate.query(SQL_SELECT_BY_ID, sessionCookieRowMapper, id).stream().findAny();
    }

    @Override
    public List<SessionCookie> findAll() {
        return simpleJDBSTemplate.query(SQL_SELECT_ALL, sessionCookieRowMapper);
    }
}
