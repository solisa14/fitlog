package com.github.solisa14.fitlogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitlogBackendApplication.class, args);
    }

    // TODO: investigate the loggedAt not being initialized properly for workouts that are saved
    // TODO: finish the CRUD endpoints (update and delete) for workouts
    // TODO: handle escaping references once done with exercise set model implementation
    // TODO: implement the frontend for the working workouts backend endpoints
}
