package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.data.dto.AccessTokenDto;
import ru.itis.javalab.data.dto.LoginPasswordDto;
import ru.itis.javalab.data.dto.RefreshTokenDto;
import ru.itis.javalab.data.services.LoginService;
import ru.itis.javalab.data.annotations.Log;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

@RestController
@Log
@CrossOrigin
public class LoginController {

    @Autowired
    protected LoginService loginService;

    @PermitAll
    @ApiOperation(value = "Get refresh JWT by login and password")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = RefreshTokenDto.class)})
    @PostMapping("/login")
    public ResponseEntity<RefreshTokenDto> login(@RequestBody LoginPasswordDto loginPasswordDto){
        return ResponseEntity.ok(loginService.login(loginPasswordDto));
    }

    @PermitAll
    @ApiOperation(value = "Get access JWT by refresh JWT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = AccessTokenDto.class)})
    @PostMapping(
            value = "/auth",
            headers = {"JWT-refresh"}
    )
    public ResponseEntity<AccessTokenDto> getAccessToken(
//            @RequestBody RefreshTokenDto refreshTokenDto
            HttpServletRequest request
    ){
        return ResponseEntity.ok(loginService.authenticate(RefreshTokenDto.builder()
                .token(request.getHeader("JWT-refresh"))
                .build()));
    }

}
