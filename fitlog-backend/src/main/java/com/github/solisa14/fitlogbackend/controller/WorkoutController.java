package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.WorkoutResponse;
import com.github.solisa14.fitlogbackend.model.Workout;
import com.github.solisa14.fitlogbackend.service.ExerciseSetService;
import com.github.solisa14.fitlogbackend.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseSetService exerciseSetService;

    public WorkoutController(WorkoutService workoutService, ExerciseSetService exerciseSetService) {
        this.workoutService = workoutService;
        this.exerciseSetService = exerciseSetService;
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
}
