package com.mpphw.springbootBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    @NotBlank(message = "Username required")
    private String username;
    @NotBlank(message = "Password required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
