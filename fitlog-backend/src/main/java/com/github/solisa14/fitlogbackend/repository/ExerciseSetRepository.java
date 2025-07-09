package com.github.solisa14.fitlogbackend.repository;

import com.github.solisa14.fitlogbackend.model.ExerciseSet;
import com.github.solisa14.fitlogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

    List<ExerciseSet> findAllByWorkoutUser(User user);

    Optional<ExerciseSet> findByIdAndWorkoutUser(Long id, User user);
}
