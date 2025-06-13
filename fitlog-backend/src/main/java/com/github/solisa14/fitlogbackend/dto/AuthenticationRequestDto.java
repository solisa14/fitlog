package com.github.solisa14.fitlogbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object for authentication requests.
 */
public class AuthenticationRequestDto {
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email should be a valid email address")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#+\\-_=])[A-Za-z\\d@$!%*?&#+\\-_=]{12,128}$",
            message = "Password should be a minimum of 12 characters with at least one lowercase, uppercase, digit, and special character")
    private String password;

    public AuthenticationRequestDto() {
    }

    public AuthenticationRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
