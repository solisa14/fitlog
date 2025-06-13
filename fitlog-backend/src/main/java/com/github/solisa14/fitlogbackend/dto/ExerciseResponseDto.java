package com.github.solisa14.fitlogbackend.dto;

import com.github.solisa14.fitlogbackend.model.Exercise;

/**
 * Data Transfer Object for exercise responses.
 */
public class ExerciseResponseDto {
    private Long id;
    private String name;
    private String description;

    public ExerciseResponseDto() {
    }

    public ExerciseResponseDto(Exercise exercise) {
        id = exercise.getId();
        name = exercise.getName();
        description = exercise.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return String.format("Exercise{name=%s, description=%s}",
                name, description);
    }

}
