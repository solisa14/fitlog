package com.github.solisa14.fitlogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitlogBackendApplication.class, args);
    }

    // TODO: remove loggedAt from workout
    // TODO: implement the frontend for the working workouts backend endpoints
}
