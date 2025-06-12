package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.ExerciseRequestDto;
import com.github.solisa14.fitlogbackend.dto.ExerciseResponseDto;
import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
        List<Exercise> foundExercises = exerciseService.getAllExercises();
        if (foundExercises.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        List<ExerciseResponseDto> exercises = foundExercises.stream().map(ExerciseResponseDto::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise
                .map(e -> ResponseEntity.status(HttpStatus.OK).body(new ExerciseResponseDto(e)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ExerciseResponseDto> createExercise(@Valid @RequestBody ExerciseRequestDto newExercise) {
        Exercise savedExercise = exerciseService.saveExercise(newExercise.convertToExercise());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ExerciseResponseDto(savedExercise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseRequestDto updatedExerciseRequest) {
        Exercise updatedExercise = updatedExerciseRequest.convertToExercise();
        Optional<Exercise> savedExercise = exerciseService.updateExercise(id, updatedExercise);
        return savedExercise
                .map(exercise -> ResponseEntity.status(HttpStatus.OK).body(new ExerciseResponseDto(exercise)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}