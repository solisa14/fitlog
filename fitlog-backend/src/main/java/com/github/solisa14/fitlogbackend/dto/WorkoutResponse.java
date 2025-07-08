package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.Workout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for workout responses.
 */
public class WorkoutResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ExerciseSetResponse> exerciseSets;

    public WorkoutResponse() {
    }

    public WorkoutResponse(Workout workout) {
        id = workout.getId();
        name = workout.getName();
        createdAt = workout.getCreatedAt();
        updatedAt = workout.getUpdatedAt();
        exerciseSets = workout.getExerciseSets().stream().map(ExerciseSetResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ExerciseSetResponse> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSetResponse> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
