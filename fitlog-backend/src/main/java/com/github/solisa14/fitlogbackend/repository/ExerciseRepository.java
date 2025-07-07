package com.github.solisa14.fitlogbackend.repository;

import com.github.solisa14.fitlogbackend.model.Exercise;
import com.github.solisa14.fitlogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByIdAndUser(Long id, User user);

    List<Exercise> findAllByUser(User user);

    @Modifying
    @Transactional
    void deleteByIdAndUser(Long id, User user);
}
