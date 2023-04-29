package com.classmanager.classservice.DTO;

import com.classmanager.classservice.model.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDTO {
        @NotBlank(message = "first name is required")
        @Size(max = 45, min = 2, message = "First name Out-of-bounds")
        private String firstName;

        @NotBlank(message = "Last name is required")
        @Size(max = 45, min = 2, message = "last name Out-of-bounds")
        private String lastName;

        private String handle;

        @Email(message = "email should be valid")
        @Size(max = 100, min = 2, message = "email Out-of-bounds")
        private String email;

        @NotNull
        @Enumerated(EnumType.ORDINAL)
        private UserRole role;

        @NotBlank(message = "password is required")
        private String password;

        @Column(length = 100)
        private String backgroundPicture;
}