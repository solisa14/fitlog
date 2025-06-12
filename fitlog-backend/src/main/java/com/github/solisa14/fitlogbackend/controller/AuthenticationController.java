package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.AuthenticationRequestDto;
import com.github.solisa14.fitlogbackend.dto.AuthenticationResponseDto;
import com.github.solisa14.fitlogbackend.dto.UserRegistrationDto;
import com.github.solisa14.fitlogbackend.model.RefreshToken;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.service.RefreshTokenService;
import com.github.solisa14.fitlogbackend.service.UserService;
import com.github.solisa14.fitlogbackend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshTokenService refreshTokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken((User) userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(
                new AuthenticationResponseDto(
                        userDetails.getUsername(),
                        token,
                        jwtUtil.getExpirationTime(),
                        refreshToken.getToken()
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.registerUser(userRegistrationDto);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegistrationDto.getEmail(), userRegistrationDto.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new AuthenticationResponseDto(
                        userDetails.getUsername(),
                        token,
                        jwtUtil.getExpirationTime(),
                        refreshToken.getToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken != null) {
            refreshTokenService.deleteByToken(refreshToken);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        return refreshTokenService.findByToken(refreshToken)
                .filter(token -> !token.isExpired())
                .map(token -> {
                    String newAccessToken = jwtUtil.generateToken(token.getUser());
                    return ResponseEntity.ok(new AuthenticationResponseDto(
                            token.getUser().getUsername(),
                            newAccessToken,
                            jwtUtil.getExpirationTime(),
                            refreshToken
                    ));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}