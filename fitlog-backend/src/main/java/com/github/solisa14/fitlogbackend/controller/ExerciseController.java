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

/**
 * Controller for handling exercise-related operations using RESTful API endpoints.
 */
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Retrieves all exercises from the database.
     *
     * @return ResponseEntity containing a list of ExerciseResponseDto objects or a NO_CONTENT
     * status if no exercises are found.
     */
    @GetMapping
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
        List<Exercise> foundExercises = exerciseService.getAllExercises();
        if (foundExercises.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        List<ExerciseResponseDto> exercises =
                foundExercises.stream().map(ExerciseResponseDto::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    /**
     * Retrieves an exercise by its ID.
     *
     * @param id the ID of the exercise to retrieve
     * @return ResponseEntity containing the ExerciseResponseDto if found, or NOT_FOUND status if
     * not found
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise
                .map(e -> ResponseEntity.status(HttpStatus.OK).body(new ExerciseResponseDto(e)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Creates a new exercise.
     *
     * @param newExercise the ExerciseRequestDto containing the details of the new exercise
     * @return ResponseEntity containing the created ExerciseResponseDto with status CREATED
     */
    @PostMapping
    public ResponseEntity<ExerciseResponseDto> createExercise(
            @Valid @RequestBody ExerciseRequestDto newExercise) {
        Exercise savedExercise = exerciseService.saveExercise(newExercise.convertToExercise());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ExerciseResponseDto(savedExercise));
    }

    /**
     * Updates an existing exercise by its ID.
     *
     * @param id                     the ID of the exercise to update
     * @param updatedExerciseRequest the ExerciseRequestDto containing the updated details of the
     *                               exercise
     * @return ResponseEntity containing the updated ExerciseResponseDto if found, or NOT_FOUND
     * status if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(@PathVariable Long id,
                                                              @Valid @RequestBody ExerciseRequestDto updatedExerciseRequest) {
        Exercise updatedExercise = updatedExerciseRequest.convertToExercise();
        Optional<Exercise> savedExercise = exerciseService.updateExercise(id, updatedExercise);
        return savedExercise
                .map(exercise -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ExerciseResponseDto(exercise)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deletes an exercise by its ID.
     *
     * @param id the ID of the exercise to delete
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
