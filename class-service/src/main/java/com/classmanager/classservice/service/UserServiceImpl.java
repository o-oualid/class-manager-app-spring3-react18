package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.exception.DuplicateKeyException;
import com.classmanager.classservice.exception.UserNotFoundException;
import com.classmanager.classservice.model.User;
import com.classmanager.classservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public RegisterUserDTO createUser(RegisterUserDTO requestUser) {
        if (userRepository.existsByEmail(requestUser.email())) {
            throw new DuplicateKeyException("Email already exists");
        }
        User user = modelMapper.map(requestUser, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, RegisterUserDTO.class);
    }


    @Override
    public UserDTO findById(String id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User retrievedUser = userOptional.get();
            return modelMapper.map(retrievedUser, UserDTO.class);
        } else {
            throw new UserNotFoundException("No user by ID: " + id);
        }
    }

    @Override
    public boolean existsByEmail(String Email) {
        return userRepository.existsByEmail(Email);
    }
}
