package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.model.Workout;
import com.github.solisa14.fitlogbackend.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.github.solisa14.fitlogbackend.util.SecurityUtil.getCurrentUser;

/**
 * Service class for managing workout operations.
 */
@Service
@Transactional(readOnly = true)
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    /**
     * Retrieves all workouts for the currently authenticated user.
     *
     * @return A list of Workout entities.
     */
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAllByUser(getCurrentUser());
    }

    /**
     * Retrieves a specific workout by its ID for the currently authenticated
     * user.
     *
     * @param id The ID of the workout to retrieve.
     * @return An Optional containing the Workout if found, otherwise empty.
     */
    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findByUserAndId(getCurrentUser(), id);
    }

    /**
     * Creates a new workout for the currently authenticated user.
     *
     * @param newWorkout The Workout entity to create.
     * @return The saved Workout entity.
     */
    @Transactional
    public Workout createWorkout(Workout newWorkout) {
        newWorkout.setUser(getCurrentUser());
        if (newWorkout.getExerciseSets() != null) {
            for (ExerciseSet set : newWorkout.getExerciseSets()) {
                set.setWorkout(newWorkout);
            }
        }
        return workoutRepository.save(newWorkout);
    }

    /**
     * Updates an existing workout for the currently authenticated user.
     *
     * @param updatedWorkout The Workout entity with updated information.
     * @param id             The ID of the workout to update.
     * @return An Optional containing the updated Workout if successful,
     * otherwise empty.
     */
    @Transactional
    public Optional<Workout> updateWorkout(Workout updatedWorkout, Long id) {
        return workoutRepository.findByUserAndId(getCurrentUser(), id).map(workout -> {
            workout.setName(updatedWorkout.getName());
            // Clear the existing collection to remove old sets
            workout.getExerciseSets().clear();
            // Add the new sets from the request
            if (updatedWorkout.getExerciseSets() != null) {
                for (ExerciseSet newSet : updatedWorkout.getExerciseSets()) {
                    newSet.setWorkout(workout);
                    workout.getExerciseSets().add(newSet);
                }
            }
            return workout;
        });
    }

    /**
     * Deletes a workout by its ID for the currently authenticated user.
     *
     * @param id The ID of the workout to delete.
     */
    @Transactional
    public void deleteWorkout(Long id) {
        workoutRepository.deleteByUserAndId(getCurrentUser(), id);
    }
}
