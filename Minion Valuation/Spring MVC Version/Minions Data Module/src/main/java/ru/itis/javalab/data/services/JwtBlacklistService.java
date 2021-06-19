package ru.itis.javalab.data.services;

public interface JwtBlacklistService {

    void add(String token);

    boolean exists(String token);

}