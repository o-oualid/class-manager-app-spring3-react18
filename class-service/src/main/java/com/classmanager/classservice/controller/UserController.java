package com.classmanager.classservice.controller;

import com.classmanager.classservice.DTO.*;
import com.classmanager.classservice.exception.ApiError;
import com.classmanager.classservice.exception.NotFoundException;
import com.classmanager.classservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/${api.version}/auth/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> isLoggedIn(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        UserDTO userDTO = userService.verifyToken(token).orElse(null);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthenticationResponseDTO> createUser(@Valid @RequestBody RegisterUserDTO requestUser) throws Exception {
            Optional<AuthenticationResponseDTO> jwtToken = userService.createUser(requestUser);
            return ResponseEntity.ok().body(jwtToken.orElse(null));
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthenticationResponseDTO> authenticateUser(@Valid @RequestBody AuthenticationRequestDTO authRequest) throws ApiError {
        Optional<AuthenticationResponseDTO> jwtToken = userService.authenticateUser(authRequest);
        return ResponseEntity.ok().body(jwtToken.orElse(null));
    }




    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) throws NotFoundException {
        UserDTO retrievedUser = userService.findById(id);
        return ResponseEntity.ok(retrievedUser);
    }
}
