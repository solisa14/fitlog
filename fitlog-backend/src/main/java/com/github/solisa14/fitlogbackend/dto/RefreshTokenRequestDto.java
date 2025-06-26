package com.github.solisa14.fitlogbackend.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for refresh token requests.
 */
public class RefreshTokenRequestDto {
    @NotBlank(message = "Refresh token should not be blank")
    private String refreshToken;

    public RefreshTokenRequestDto() {
    }

    public RefreshTokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
