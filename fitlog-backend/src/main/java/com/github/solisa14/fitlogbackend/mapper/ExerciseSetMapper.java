package com.github.solisa14.fitlogbackend.mapper;

import com.github.solisa14.fitlogbackend.dto.ExerciseSetRequest;
import com.github.solisa14.fitlogbackend.dto.ExerciseSetResponse;
import com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException;
import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.repository.ExerciseRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ExerciseSetMapper {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Mapping(source = "exerciseId", target = "exercise")
    public abstract ExerciseSet toExerciseSet(ExerciseSetRequest exerciseSetRequest);

    public abstract ExerciseSetResponse toResponseDto(ExerciseSet exerciseSet);

    public Exercise longToExercise(Long exerciseId) {
        if (exerciseId == null) {
            return null;
        }
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise with ID {" + exerciseId + "} not found."));
    }
}
