package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.*;
import com.classmanager.classservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<UserDTO> verifyToken(String token) throws Exception;
    Optional<AuthenticationResponseDTO> createUser(RegisterUserDTO requestUser) throws Exception;
    Optional<AuthenticationResponseDTO> authenticateUser(AuthenticationRequestDTO request) throws NotFoundException;
    UserDTO findById(String id) throws NotFoundException;
    boolean existsByEmail(String email);
}
