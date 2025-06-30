package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.model.Exercise;
import jakarta.validation.constraints.*;

import java.util.Set;

/**
 * Data Transfer Object for exercise creation and update requests.
 */
public class ExerciseRequestDto {
    @NotBlank(message = "Exercise name should not be blank")
    @Size(max = 50, message = "Exercise name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$",
            message = "Exercise name should contain only letters, numbers, spaces, hyphens and underscores")
    private String name;

    @NotEmpty(message = "There should be at least one or more muscle group for this exercise.")
    private Set<MuscleGroup> muscleGroups;

    @NotNull(message = "Exercise must have a tracking type.")
    private TrackingType trackingType;


    public ExerciseRequestDto() {
    }

    public ExerciseRequestDto(Exercise exercise) {
        name = exercise.getName();
        muscleGroups = exercise.getMuscleGroups();
        trackingType = exercise.getTrackingType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public TrackingType getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(TrackingType trackingType) {
        this.trackingType = trackingType;
    }
}
