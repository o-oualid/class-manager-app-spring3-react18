package com.classmanager.classservice.controller;

import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.ApiError;
import com.classmanager.classservice.exception.DuplicateKeyException;
import com.classmanager.classservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegisterUserDTO> createUser(@Valid @RequestBody RegisterUserDTO requestUser) throws ApiError {
        if (userService.existsByEmail(requestUser.email())) {
            throw new DuplicateKeyException("Email");
        }
        return ResponseEntity.ok().body(userService.createUser(requestUser));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        UserDTO retrievedUser = userService.findById(id);
        return ResponseEntity.ok(retrievedUser);
    }
}
