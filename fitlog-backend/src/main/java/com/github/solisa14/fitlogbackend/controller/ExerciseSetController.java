package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.ExerciseSetResponse;
import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.service.ExerciseSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * This controller provides endpoints for retrieving exercise set data. Exercise
 * sets represent individual sets performed during workouts, including details
 * such as reps, weight, duration, and other tracking metrics.
 */
@RestController
@RequestMapping("/api/v1/exercise-sets")
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    /**
     * Constructs a new ExerciseSetController with the specified service.
     *
     * @param exerciseSetService the service for handling exercise set
     *                           operations
     */
    public ExerciseSetController(ExerciseSetService exerciseSetService) {
        this.exerciseSetService = exerciseSetService;
    }

    /**
     * Retrieves all exercise sets.
     * <p>
     * This endpoint returns a list of all exercise sets in the system,
     * converted to ExerciseSetResponse DTOs for API consumption.
     *
     * @return ResponseEntity containing a list of ExerciseSetResponse objects
     * with HTTP status 200 (OK) if successful
     */
    @GetMapping
    public ResponseEntity<List<ExerciseSetResponse>> getAllExerciseSets() {
        List<ExerciseSetResponse> exerciseSetList = exerciseSetService.getAllExerciseSets().stream().map(ExerciseSetResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(exerciseSetList);
    }

    /**
     * Retrieves an exercise set by its unique identifier.
     * <p>
     * This endpoint returns an ExerciseSetResponse based on the provided ID if
     * the exercise set exists. If no exercise set is found, it returns a 404
     * (Not Found) response.
     *
     * @param id the unique identifier of the exercise set to be retrieved
     * @return ResponseEntity containing an ExerciseSetResponse object with HTTP
     * status 200 (OK) if the exercise set is found, otherwise an empty response
     * with HTTP status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseSetResponse> getExerciseSetById(@PathVariable Long id) {
        Optional<ExerciseSet> exerciseSet = exerciseSetService.getExerciseSetById(id);
        return exerciseSet
                .map(ex -> ResponseEntity.status(HttpStatus.OK).body(new ExerciseSetResponse(ex)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
