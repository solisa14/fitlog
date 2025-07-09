package com.github.solisa14.fitlogbackend.mapper;

import com.github.solisa14.fitlogbackend.dto.WorkoutRequest;
import com.github.solisa14.fitlogbackend.dto.WorkoutResponse;
import com.github.solisa14.fitlogbackend.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExerciseSetMapper.class})
public interface WorkoutMapper {
    Workout toWorkout(WorkoutRequest workoutRequest);

    WorkoutResponse toResponseDto(Workout workout);
}