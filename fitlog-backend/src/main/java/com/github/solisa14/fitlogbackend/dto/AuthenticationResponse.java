package com.github.solisa14.fitlogbackend.dto;

/**
 * Data Transfer Object for authentication responses.
 */
public class AuthenticationResponse {

    private String token;
    private String username;
    private long expirationTime;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String username, String token, long expirationTime) {
        this.username = username;
        this.token = token;
        this.expirationTime = expirationTime;
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
