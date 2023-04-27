package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterUserDTO createUser(RegisterUserDTO requestUser);
    UserDTO findById(String id) throws UserNotFoundException;
    boolean existsByEmail(String email);
}
