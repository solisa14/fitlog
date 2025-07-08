package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.Workout;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for workout creation and update requests.
 */
public class WorkoutRequest {

    @NotBlank(message = "Workout name should not be blank")
    @Size(max = 255, message = "Workout name should not exceed 255 characters")
    private String name;

    @NotEmpty(message = "Workout should contain at least one exercise set")
    private List<ExerciseSetRequest> exerciseSets;

    public WorkoutRequest() {
    }

    public WorkoutRequest(Workout workout) {
        name = workout.getName();
        exerciseSets = workout.getExerciseSets().stream().map(ExerciseSetRequest::new).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseSetRequest> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSetRequest> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
