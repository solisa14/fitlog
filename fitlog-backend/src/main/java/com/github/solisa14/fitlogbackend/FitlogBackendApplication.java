package com.github.solisa14.fitlogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitlogBackendApplication.class, args);
    }

    // TODO: implement the Workout entity and restructure API endpoints to go through a workout controller instead of a dedicated ExerciseSetController
    // TODO: handle escaping references once done with exercise set model implementation
}
