package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.model.ExerciseSet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Data Transfer Object for exercise set responses.
 */
public class ExerciseSetResponse {

    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private Set<MuscleGroup> muscleGroups;
    private TrackingType trackingType;
    private LocalDateTime loggedAt;
    private int setNumber;
    private Integer reps;
    private Double weight;
    private Double rpe;
    private Duration duration;
    private Double distance;

    public ExerciseSetResponse() {
    }

    public ExerciseSetResponse(ExerciseSet exerciseSet) {
        id = exerciseSet.getId();
        exerciseId = exerciseSet.getExercise().getId();
        exerciseName = exerciseSet.getExerciseName();
        muscleGroups = exerciseSet.getMuscleGroups();
        trackingType = exerciseSet.getTrackingType();
        loggedAt = exerciseSet.getLoggedAt();
        setNumber = exerciseSet.getSetNumber();
        reps = exerciseSet.getReps();
        weight = exerciseSet.getWeight();
        rpe = exerciseSet.getRpe();
        duration = exerciseSet.getDuration();
        distance = exerciseSet.getDistance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getRpe() {
        return rpe;
    }

    public void setRpe(Double rpe) {
        this.rpe = rpe;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
