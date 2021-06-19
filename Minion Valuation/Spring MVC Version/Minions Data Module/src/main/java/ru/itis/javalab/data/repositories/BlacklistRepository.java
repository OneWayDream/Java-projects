package ru.itis.javalab.data.repositories;

public interface BlacklistRepository {

    void save(String token);

    boolean exists(String token);

}
