package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.ExerciseRequest;
import com.github.solisa14.fitlogbackend.dto.ExerciseResponse;
import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.solisa14.fitlogbackend.util.SecurityUtil.getCurrentUser;

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
     * @return ResponseEntity containing a list of ExerciseResponse objects or a NO_CONTENT
     * status if no exercises are found.
     */
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAllExercises() {
        List<Exercise> foundExercises = exerciseService.getAllExercises();
        List<ExerciseResponse> exercises =
                foundExercises.stream().map(ExerciseResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    /**
     * Retrieves an exercise by its ID.
     *
     * @param id the ID of the exercise to retrieve
     * @return ResponseEntity containing the ExerciseResponse if found, or NOT_FOUND status if
     * not found
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseResponse> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise
                .map(ex -> ResponseEntity.status(HttpStatus.OK).body(new ExerciseResponse(ex)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Creates a new exercise.
     *
     * @param newExercise the ExerciseRequest containing the details of the new exercise
     * @return ResponseEntity containing the created ExerciseResponse with status CREATED
     */
    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(
            @Valid @RequestBody ExerciseRequest newExercise) {
        Exercise savedExercise = exerciseService.saveExercise(
                new Exercise(newExercise.getName(),
                        newExercise.getMuscleGroups(),
                        newExercise.getTrackingType(),
                        getCurrentUser()
                ));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ExerciseResponse(savedExercise));
    }

    /**
     * Updates an existing exercise by its ID.
     *
     * @param id                     the ID of the exercise to update
     * @param updatedExerciseRequest the ExerciseRequest containing the updated details of the
     *                               exercise
     * @return ResponseEntity containing the updated ExerciseResponse if found, or NOT_FOUND
     * status if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponse> updateExercise(@PathVariable Long id,
                                                           @Valid @RequestBody ExerciseRequest updatedExerciseRequest) {
        Exercise updatedExercise = new Exercise(updatedExerciseRequest.getName(),
                updatedExerciseRequest.getMuscleGroups(),
                updatedExerciseRequest.getTrackingType(),
                getCurrentUser()
        );
        Optional<Exercise> savedExercise = exerciseService.updateExercise(id, updatedExercise);
        return savedExercise
                .map(exercise -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ExerciseResponse(exercise)))
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
