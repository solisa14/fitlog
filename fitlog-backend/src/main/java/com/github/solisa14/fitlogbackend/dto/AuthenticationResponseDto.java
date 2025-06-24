package com.github.solisa14.fitlogbackend.dto;

/**
 * Data Transfer Object for authentication responses.
 */
public class AuthenticationResponseDto {
    private String token;
    private String username;
    private long expirationTime;
    private String refreshToken;

    public AuthenticationResponseDto() {
    }

    public AuthenticationResponseDto(String username, String token, long expirationTime,
                                     String refreshToken) {
        this.username = username;
        this.token = token;
        this.expirationTime = expirationTime;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
