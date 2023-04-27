package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO createUser(UserDTO requestUser);
    User findById(String id);
    boolean existsByEmail(String email);
}
