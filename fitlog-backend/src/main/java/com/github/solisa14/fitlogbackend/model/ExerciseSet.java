package com.github.solisa14.fitlogbackend.model;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.exception.TrackingTypeMismatchException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents an exercise set in the application. Each exercise set is
 * associated with an exercise and has a set number, reps, weight, RPE,
 * duration, and distance.
 */
@Entity
@Table(name = "exercise_set")
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(name = "logged_at")
    private LocalDateTime loggedAt;

    @Min(value = 1)
    @Column(name = "set_number")
    private int setNumber;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "rpe")
    private Double rpe; // Rate of Perceived Exertion

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "distance")
    private Double distance;

    public ExerciseSet() {
    }

    public ExerciseSet(Exercise exercise, Integer reps, Double weight, Double rpe, Duration duration, Double distance) {
        this.reps = reps;
        this.exercise = exercise;
        this.weight = weight;
        this.rpe = rpe;
        this.loggedAt = LocalDateTime.now();
        this.duration = duration;
        this.distance = distance;
    }

    public void logSet(Integer reps, Double weight, Double rpe) {
        if (exercise.getTrackingType().equals(TrackingType.REPS_AND_WEIGHT)) {
            setReps(reps);
            setWeight(weight);
            setRpe(rpe);
        } else {
            throw new TrackingTypeMismatchException("The tracking type \"" + exercise.getTrackingType() + "\" cannot use reps, weight, and RPE for tracking.");
        }
    }

    public void logSet(Duration duration) {
        if (exercise.getTrackingType().equals(TrackingType.TIME_BASED)) {
            setDuration(duration);
        } else {
            throw new TrackingTypeMismatchException("The tracking type \"" + exercise.getTrackingType() + "\" cannot use duration for tracking.");
        }
    }

    public void logSet(Duration duration, Double distance) {
        if (exercise.getTrackingType().equals(TrackingType.DISTANCE_AND_DURATION)) {
            setDuration(duration);
            setDistance(distance);
        } else {
            throw new TrackingTypeMismatchException("The tracking type \"" + exercise.getTrackingType() + "\" cannot use duration and distance for tracking.");
        }
    }

    public void logSet(Integer reps, Double rpe) {
        if (exercise.getTrackingType().equals(TrackingType.REPS_ONLY)) {
            setReps(reps);
            setRpe(rpe);
        } else {
            throw new TrackingTypeMismatchException("The tracking type \"" + exercise.getTrackingType() + "\" cannot use reps and RPE for tracking.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
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

    public String getExerciseName() {
        return exercise.getName();
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return exercise.getMuscleGroups();
    }

    public TrackingType getTrackingType() {
        return exercise.getTrackingType();
    }

}
