package com.classmanager.classservice.DTO;

import com.classmanager.classservice.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDTO {
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

        @Enumerated(EnumType.ORDINAL)
        private UserRole role;

        @Column(length = 100)
        private String backgroundPicture;

}
