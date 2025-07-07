package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

/**
 * Data Transfer Object for exercise set creation and update requests.
 */
public class ExerciseSetRequest {

    @NotNull(message = "Exercise ID should not be null")
    private Long exerciseId;

    @Min(value = 1, message = "Set number should be at least 1")
    private int setNumber;

    @Min(value = 0, message = "Reps should not be negative")
    private Integer reps;

    @Min(value = 0, message = "Weight should not be negative")
    private Double weight;

    @Min(value = 0, message = "RPE should not be negative")
    @Max(value = 10, message = "RPE should not be greater than 10")
    private Double rpe;

    private Duration duration;

    @Min(value = 0, message = "Distance should not be negative")
    private Double distance;

    public ExerciseSetRequest() {
    }

    public ExerciseSetRequest(ExerciseSet exerciseSet) {
        exerciseId = exerciseSet.getExercise().getId();
        setNumber = exerciseSet.getSetNumber();
        reps = exerciseSet.getReps();
        weight = exerciseSet.getWeight();
        rpe = exerciseSet.getRpe();
        duration = exerciseSet.getDuration();
        distance = exerciseSet.getDistance();
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
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
