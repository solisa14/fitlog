package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // TODO: add GET, PUT, DELETE endpoints for user management
}
