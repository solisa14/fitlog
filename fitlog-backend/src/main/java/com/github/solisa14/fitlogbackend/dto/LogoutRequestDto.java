package com.github.solisa14.fitlogbackend.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for logout requests.
 */
public class LogoutRequestDto {
    @NotBlank(message = "Refresh token should not be blank")
    private String refreshToken;

    public LogoutRequestDto() {
    }

    public LogoutRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
