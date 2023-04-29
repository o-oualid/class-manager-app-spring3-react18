package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.AuthenticationRequestDTO;
import com.classmanager.classservice.DTO.AuthenticationResponseDTO;
import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<AuthenticationResponseDTO> createUser(RegisterUserDTO requestUser) throws Exception;
    Optional<AuthenticationResponseDTO> authenticateUser(AuthenticationRequestDTO request);
    UserDTO findById(String id) throws NotFoundException;
    boolean existsByEmail(String email);
}
