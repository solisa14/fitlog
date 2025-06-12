package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.repository.ExerciseRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAllByUser(getCurrentUser());
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findByIdAndUser(id, getCurrentUser());
    }

    public Optional<Exercise> updateExercise(Long id, Exercise updatedExercise) {
        return exerciseRepository.findByIdAndUser(id, getCurrentUser())
                .map(exercise -> {
                    exercise.setName(updatedExercise.getName());
                    exercise.setDescription(updatedExercise.getDescription());
                    return exerciseRepository.save(exercise);
                });
    }

    public Exercise saveExercise(Exercise newExercise) {
        newExercise.setUser(getCurrentUser());
        return exerciseRepository.save(newExercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteByIdAndUser(id, getCurrentUser());
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}