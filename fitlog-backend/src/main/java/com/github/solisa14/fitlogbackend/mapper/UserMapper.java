package com.github.solisa14.fitlogbackend.mapper;

import com.github.solisa14.fitlogbackend.dto.AuthenticationRequest;
import com.github.solisa14.fitlogbackend.dto.AuthenticationResponse;
import com.github.solisa14.fitlogbackend.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(AuthenticationRequest authenticationRequest);

    AuthenticationResponse toResponseDto(User user, String token);
}
