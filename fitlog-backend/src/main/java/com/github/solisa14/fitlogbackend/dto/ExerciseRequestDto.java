package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExerciseRequestDto {
    @NotBlank(message = "Exercise name should not be blank")
    @Size(max = 50, message = "Exercise name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Exercise name should contain only letters, numbers, spaces, hyphens and underscores")
    private String name;

    @NotNull(message = "Exercise description should not be null")
    @Size(max = 500, message = "Exercise description should not exceed 500 characters")
    private String description;


    public ExerciseRequestDto() {
    }

    public ExerciseRequestDto(Exercise exercise) {
        name = exercise.getName();
        description = exercise.getDescription();
    }

    public Exercise convertToExercise() {
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new Exercise(name, description, currentUser);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Exercise{name=%s, description=%s}",
                name, description);
    }

}
