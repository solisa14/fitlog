package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.model.Workout;
import com.github.solisa14.fitlogbackend.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.github.solisa14.fitlogbackend.util.SecurityUtil.getCurrentUser;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAllByUser(getCurrentUser());
    }

    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findByUserAndId(getCurrentUser(), id);
    }

    public Workout createWorkout(Workout newWorkout) {
        newWorkout.setUser(getCurrentUser());
        if (newWorkout.getExerciseSets() != null) {
            for (ExerciseSet set : newWorkout.getExerciseSets()) {
                set.setWorkout(newWorkout);
            }
        }
        return workoutRepository.save(newWorkout);
    }

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
}

