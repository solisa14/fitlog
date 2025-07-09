package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.github.solisa14.fitlogbackend.util.SecurityUtil.getCurrentUser;

/**
 * Service class for managing exercises. It handles CRUD operations for
 * exercises, ensuring that operations are performed for the currently
 * authenticated user.
 */
@Service
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Retrieves all exercises associated with the currently authenticated user.
     *
     * @return A list of Exercise entities.
     */
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAllByUser(getCurrentUser());
    }

    /**
     * Retrieves a specific exercise by its ID, for the currently authenticated
     * user.
     *
     * @param id The ID of the exercise to retrieve.
     * @return An Optional containing the Exercise if found, otherwise empty.
     */
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findByIdAndUser(id, getCurrentUser());
    }

    /**
     * Updates an existing exercise for the currently authenticated user. It
     * finds the exercise by ID and user, then updates its name and description.
     *
     * @param id              The ID of the exercise to update.
     * @param updatedExercise The Exercise entity with updated information.
     * @return An Optional containing the updated Exercise if successful,
     * otherwise empty.
     */
    @Transactional
    public Optional<Exercise> updateExercise(Long id, Exercise updatedExercise) {
        // Find the exercise by ID and current user before updating
        return exerciseRepository.findByIdAndUser(id, getCurrentUser()).map(exercise -> {
            exercise.setName(updatedExercise.getName());
            exercise.setMuscleGroups(updatedExercise.getMuscleGroups());
            exercise.setTrackingType(updatedExercise.getTrackingType());
            return exerciseRepository.save(exercise);
        });
    }

    /**
     * Saves a new exercise, associating it with the currently authenticated
     * user.
     *
     * @param newExercise The Exercise entity to save.
     * @return The saved Exercise entity.
     */
    @Transactional
    public Exercise saveExercise(Exercise newExercise) {
        newExercise.setUser(getCurrentUser()); // Set the current user to the new exercise
        return exerciseRepository.save(newExercise);
    }

    /**
     * Deletes an exercise by its ID, for the currently authenticated user.
     *
     * @param id The ID of the exercise to delete.
     */
    @Transactional
    public void deleteExercise(Long id) {
        exerciseRepository.deleteByIdAndUser(id, getCurrentUser());
    }
}
