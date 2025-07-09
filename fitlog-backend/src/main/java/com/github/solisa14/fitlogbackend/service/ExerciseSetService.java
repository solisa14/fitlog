package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.repository.ExerciseSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.github.solisa14.fitlogbackend.util.SecurityUtil.getCurrentUser;

/**
 * Service class for managing exercise set operations.
 */
@Service
@Transactional(readOnly = true)
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    public ExerciseSetService(ExerciseSetRepository exerciseSetRepository) {
        this.exerciseSetRepository = exerciseSetRepository;
    }

    /**
     * Retrieves all exercise sets for the current user.
     *
     * @return List of ExerciseSet objects
     */
    public List<ExerciseSet> getAllExerciseSets() {
        return exerciseSetRepository.findAllByWorkoutUser(getCurrentUser());
    }

    /**
     * Retrieves an exercise set by its unique identifier.
     *
     * @param id the unique identifier of the exercise set to be retrieved
     * @return Optional containing the ExerciseSet if found, otherwise empty
     */
    public Optional<ExerciseSet> getExerciseSetById(Long id) {
        return exerciseSetRepository.findByIdAndWorkoutUser(id, getCurrentUser());
    }
}
