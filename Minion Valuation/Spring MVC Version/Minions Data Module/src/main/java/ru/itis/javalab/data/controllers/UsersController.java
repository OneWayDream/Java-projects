package ru.itis.javalab.data.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.data.services.UsersService;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Getting all bazaar items.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting")})
    @PostMapping(
            value = "/users/{user-id}/block",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> blockUser(@PathVariable("user-id") Long userId) {
        usersService.blockUser(userId);
        return ResponseEntity.ok().build();
    }
}
