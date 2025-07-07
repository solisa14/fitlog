package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.model.Workout;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Data Transfer Object for workout creation and update requests.
 */
public class WorkoutRequest {

    @NotBlank(message = "Workout name should not be blank")
    @Size(max = 255, message = "Workout name should not exceed 255 characters")
    private String name;

    @NotEmpty(message = "Workout should contain at least one exercise set")
    private List<Long> exerciseSetIds;

    public WorkoutRequest() {
    }

    public WorkoutRequest(Workout workout) {
        name = workout.getName();
        exerciseSetIds = workout.getExerciseSet().stream()
                .map(ExerciseSet::getId)
                .toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getExerciseSetIds() {
        return exerciseSetIds;
    }

    public void setExerciseSetIds(List<Long> exerciseSetIds) {
        this.exerciseSetIds = exerciseSetIds;
    }
}
