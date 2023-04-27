package com.classmanager.classservice.DTO;

import com.classmanager.classservice.model.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record RegisterUserDTO(
        @NotBlank(message = "first name is required")
        @Size(max=45,min=2,message="First name Out-of-bounds")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max=45,min=2,message="last name Out-of-bounds")
        String lastName,

        String handle,

        @Email(message = "email should be valid")
        @Size(max=100,min=2,message="email Out-of-bounds")
        String email,

        @Enumerated(EnumType.ORDINAL)
        UserRole role,

        @NotBlank(message = "password is required")
        String password,

        @Column(length = 100)
        String backgroundPicture
) {
}
