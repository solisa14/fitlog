package com.github.solisa14.fitlogbackend.mapper;

import com.github.solisa14.fitlogbackend.dto.ExerciseRequest;
import com.github.solisa14.fitlogbackend.dto.ExerciseResponse;
import com.github.solisa14.fitlogbackend.model.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    Exercise toExercise(ExerciseRequest exerciseRequest);

    ExerciseResponse toResponseDto(Exercise exercise);
}
