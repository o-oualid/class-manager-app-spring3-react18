package com.classmanager.classservice.controller;

import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.ApiError;
import com.classmanager.classservice.exception.DuplicateKeyException;
import com.classmanager.classservice.exception.UncaughtException;
import com.classmanager.classservice.model.User;
import com.classmanager.classservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO requestUser) throws ApiError {
        if (userService.existsByEmail(requestUser.email())) {
            throw new DuplicateKeyException("Email");
        }
        return ResponseEntity.ok().body(userService.createUser(requestUser));
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        return ResponseEntity.ofNullable(userService.findById(id));
    }
}
