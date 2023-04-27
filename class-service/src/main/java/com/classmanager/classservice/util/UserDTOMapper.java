package com.classmanager.classservice.util;

import com.classmanager.classservice.DTO.UserDTO;
import com.classmanager.classservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDTOMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getHandle(),
                user.getEmail(),
                user.getRole(),
                user.getBackgroundPicture()
        );
    }

    public User toEntity(UserDTO userDTO) throws IllegalArgumentException {
        return new User(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.handle(),
                userDTO.email(),
                userDTO.password(),
                userDTO.role(),
                userDTO.backgroundPicture()
        );
    }
}
