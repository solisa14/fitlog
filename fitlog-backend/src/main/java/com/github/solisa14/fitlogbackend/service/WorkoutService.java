package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.model.Workout;
import com.github.solisa14.fitlogbackend.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

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
}
