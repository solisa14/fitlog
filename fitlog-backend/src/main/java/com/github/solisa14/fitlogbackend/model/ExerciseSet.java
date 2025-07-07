package com.github.solisa14.fitlogbackend.model;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.exception.TrackingTypeMismatchException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

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
    private int reps;

    @Column(name = "weight")
    private double weight;

    @Column(name = "rpe")
    private double rpe; // Rate of Perceived Exertion

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "distance")
    private double distance;

    public ExerciseSet() {
    }

    public ExerciseSet(Exercise exercise, int reps, double weight, double rpe, Duration duration, double distance) {
        this.reps = reps;
        this.exercise = exercise;
        this.weight = weight;
        this.rpe = rpe;
        this.loggedAt = LocalDateTime.now();
        this.duration = duration;
        this.distance = distance;
    }

    public void logSet(int reps, double weight, double rpe) {
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

    public void logSet(Duration duration, double distance) {
        if (exercise.getTrackingType().equals(TrackingType.DISTANCE_AND_DURATION)) {
            setDuration(duration);
            setDistance(distance);
        } else {
            throw new TrackingTypeMismatchException("The tracking type \"" + exercise.getTrackingType() + "\" cannot use duration and distance for tracking.");
        }
    }

    public void logSet(int reps, double rpe) {
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

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getRpe() {
        return rpe;
    }

    public void setRpe(double rpe) {
        this.rpe = rpe;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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
