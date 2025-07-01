package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.model.Exercise;

import java.util.Set;

/**
 * Data Transfer Object for exercise responses.
 */
public class ExerciseResponse {
    private Long id;
    private String name;
    private Set<MuscleGroup> muscleGroups;
    private TrackingType trackingType;

    public ExerciseResponse() {
    }

    public ExerciseResponse(Exercise exercise) {
        id = exercise.getId();
        name = exercise.getName();
        muscleGroups = exercise.getMuscleGroups();
        trackingType = exercise.getTrackingType();
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
