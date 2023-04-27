package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.UserNotFoundException;
import com.classmanager.classservice.model.User;
import com.classmanager.classservice.repository.UserRepository;
import com.classmanager.classservice.util.UserDTOMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper  = userDTOMapper;
    }

    @Override
    public UserDTO createUser(UserDTO requestUser) {
        User user = userDTOMapper.toEntity(requestUser);
        User savedUser = userRepository.save(user);
        return userDTOMapper.toDTO(savedUser);
    }

    @SneakyThrows
    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user by ID: " + id));
    }

    @Override
    public boolean existsByEmail(String Email) {
        return userRepository.existsByEmail(Email);
    }
}
