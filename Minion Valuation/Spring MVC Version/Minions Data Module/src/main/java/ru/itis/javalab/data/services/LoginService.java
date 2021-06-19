package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.AccessTokenDto;
import ru.itis.javalab.data.dto.LoginPasswordDto;
import ru.itis.javalab.data.dto.RefreshTokenDto;

public interface LoginService {
    RefreshTokenDto login(LoginPasswordDto emailPasswordDto);
    AccessTokenDto authenticate(RefreshTokenDto refreshTokenDto);
}
