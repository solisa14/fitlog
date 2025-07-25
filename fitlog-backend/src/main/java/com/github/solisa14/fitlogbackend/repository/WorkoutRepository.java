package com.github.solisa14.fitlogbackend.repository;

import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface
WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findAllByUser(User user);

    Optional<Workout> findByUserAndId(User user, Long id);

    @Modifying
    @Transactional
    void deleteByUserAndId(User currentUser, Long id);
}
