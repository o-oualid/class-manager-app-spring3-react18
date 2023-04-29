package com.classmanager.classservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequestDTO(
        @Email(message = "email should be valid")
        @Size(max=100,min=2,message="email Out-of-bounds")
        String email,

        @NotBlank(message = "password is required")
        String password
) {
}