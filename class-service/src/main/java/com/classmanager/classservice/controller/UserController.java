package com.classmanager.classservice.controller;

import com.classmanager.classservice.DTO.AuthenticationRequestDTO;
import com.classmanager.classservice.DTO.AuthenticationResponseDTO;
import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.ApiError;
import com.classmanager.classservice.exception.NotFoundException;
import com.classmanager.classservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/${api.version}/auth/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) throws NotFoundException {
        UserDTO retrievedUser = userService.findById(id);
        return ResponseEntity.ok(retrievedUser);
    }
}
