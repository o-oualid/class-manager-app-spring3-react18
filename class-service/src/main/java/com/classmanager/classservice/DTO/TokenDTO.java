package com.classmanager.classservice.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenDTO {
    @NotBlank(message = "invalid token value")
    private String value;
}
