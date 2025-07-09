package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.WorkoutRequest;
import com.github.solisa14.fitlogbackend.dto.WorkoutResponse;
import com.github.solisa14.fitlogbackend.mapper.WorkoutMapper;
import com.github.solisa14.fitlogbackend.model.Workout;
import com.github.solisa14.fitlogbackend.service.ExerciseSetService;
import com.github.solisa14.fitlogbackend.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseSetService exerciseSetService;
    private final WorkoutMapper workoutMapper;

    public WorkoutController(WorkoutService workoutService, ExerciseSetService exerciseSetService, WorkoutMapper workoutMapper) {
        this.workoutService = workoutService;
        this.exerciseSetService = exerciseSetService;
        this.workoutMapper = workoutMapper;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getAllWorkouts() {
        List<WorkoutResponse> workoutList = workoutService.getAllWorkouts().stream().map(WorkoutResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(workoutList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(@PathVariable Long id) {
        Optional<Workout> workout = workoutService.getWorkoutById(id);
        return workout
                .map(w -> ResponseEntity.status(HttpStatus.OK).body(new WorkoutResponse(w)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(@RequestBody @Valid WorkoutRequest workoutRequest) {
        Workout newWorkout = workoutMapper.toWorkout(workoutRequest);
        WorkoutResponse createdWorkout = workoutMapper.toResponseDto(workoutService.createWorkout(newWorkout));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }
}
