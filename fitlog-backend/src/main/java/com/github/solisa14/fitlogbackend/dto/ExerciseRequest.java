package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import com.github.solisa14.fitlogbackend.model.Exercise;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Data Transfer Object for exercise creation and update requests.
 */
public class ExerciseRequest {

  @NotBlank(message = "Exercise name should not be blank")
  @Size(max = 50, message = "Exercise name should not exceed 50 characters")
  @Pattern(regexp = "^[a-zA-Z0-9\\s\\-()]+$",
    message = "Exercise name should contain only letters, numbers, spaces, hyphens, and parentheses")
  private String name;

  @NotEmpty(message = "There should be at least one or more muscle group for this exercise.")
  private Set<MuscleGroup> muscleGroups;

  @NotNull(message = "Exercise must have a tracking type.")
  private TrackingType trackingType;

  public ExerciseRequest() {
  }

  public ExerciseRequest(Exercise exercise) {
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
    return new HashSet<>(muscleGroups);
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
