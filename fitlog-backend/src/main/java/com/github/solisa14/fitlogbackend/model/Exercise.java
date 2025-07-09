package com.github.solisa14.fitlogbackend.model;

import com.github.solisa14.fitlogbackend.enums.MuscleGroup;
import com.github.solisa14.fitlogbackend.enums.TrackingType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents an exercise entity in the application. Each exercise is associated
 * with a user and has a name set of muscle groups and a tracking type to be
 * used for dynamically tracking each set.
 */
@Entity(name = "exercise")
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_muscle_groups")
    private Set<MuscleGroup> muscleGroups;

    @Enumerated(EnumType.STRING)
    private TrackingType trackingType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // Specifies the foreign key column in the exercise table
    private User user;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public Exercise() {
    }

    public Exercise(Exercise exercise) {
        this.name = exercise.name;
        this.muscleGroups = exercise.muscleGroups;
        this.trackingType = exercise.trackingType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public TrackingType getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(TrackingType trackingType) {
        this.trackingType = trackingType;
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

    @Override
    public String toString() {
        return String.format("Exercise{"
                + "id=%d, "
                + "name='%s', "
                + "muscleGroups=%s, "
                + "trackingType=%s"
                + "}", id, name, muscleGroups.toString(), trackingType.toString());
    }
}
