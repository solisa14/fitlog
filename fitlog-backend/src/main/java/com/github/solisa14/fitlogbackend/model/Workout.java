package com.github.solisa14.fitlogbackend.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> exerciseSets;

    @Column
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public Workout(User user, List<ExerciseSet> exerciseSets, String name) {
        this.user = user;
        this.exerciseSets = exerciseSets;
        this.name = name;
    }

    public Workout() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ExerciseSet> getExerciseSet() {
        return exerciseSets;
    }

    public void setExerciseSet(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExerciseSet(ExerciseSet exerciseSet) {
        this.exerciseSets.add(exerciseSet);
        exerciseSet.setWorkout(this);
    }

    public void removeExerciseSet(ExerciseSet exerciseSet) {
        this.exerciseSets.remove(exerciseSet);
        exerciseSet.setWorkout(null);
    }

    public Duration getTotalDuration() {
        return exerciseSets.stream()
                .map(ExerciseSet::getDuration)
                .filter(Objects::nonNull)
                .reduce(Duration.ZERO, Duration::plus);
    }
}
