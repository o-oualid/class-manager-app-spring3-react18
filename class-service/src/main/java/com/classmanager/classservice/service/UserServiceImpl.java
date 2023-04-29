package com.classmanager.classservice.service;

import com.classmanager.classservice.DTO.AuthenticationRequestDTO;
import com.classmanager.classservice.DTO.AuthenticationResponseDTO;
import com.classmanager.classservice.DTO.RegisterUserDTO;
import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.config.JwtService;
import com.classmanager.classservice.exception.DuplicateKeyException;
import com.classmanager.classservice.exception.UncaughtException;
import com.classmanager.classservice.exception.NotFoundException;
import com.classmanager.classservice.model.User;
import com.classmanager.classservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Optional<AuthenticationResponseDTO> createUser(RegisterUserDTO requestUser) throws Exception {
        if (userRepository.existsByEmail(requestUser.getEmail())) {
            throw new DuplicateKeyException("Email");
        }
            User user = modelMapper.map(requestUser, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            return Optional.of(savedUser)
                .map(newUser -> {
                    var jwtToken = jwtService.generateToken(newUser);
                    return Optional.of(new AuthenticationResponseDTO(jwtToken));
                })
                .orElseThrow(() -> new UncaughtException("Failed to save user"));
    }

    @Override
    public Optional<AuthenticationResponseDTO> authenticateUser(AuthenticationRequestDTO authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );
        User user = userRepository.findByEmail(authRequest.email()).orElseThrow(() ->
                new NotFoundException("user not found")
        );
        var jwtToken = jwtService.generateToken(user);
        return Optional.of(new AuthenticationResponseDTO(jwtToken));
    }


    @Override
    public UserDTO findById(String id) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("No user by ID: " + id);
        }
            User retrievedUser = userOptional.get();
            return modelMapper.map(retrievedUser, UserDTO.class);
    }

    @Override
    public boolean existsByEmail(String Email) {
        return userRepository.existsByEmail(Email);
    }
}
