package com.github.solisa14.fitlogbackend.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents an exercise entity in the application. Each exercise is associated with a user and has
 * a name set of muscle groups and a tracking type to be used for dynamically tracking each set.
 */
@Entity(name = "exercise")
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_muscle_groups")
    private Set<MuscleGroup> muscleGroups;

    @Enumerated(EnumType.STRING)
    private TrackingType trackingType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // Specifies the foreign key column in the exercise table
    private User user;

    public Exercise() {
    }

    public Exercise(String name, Set<MuscleGroup> muscleGroups, TrackingType trackingType, User user) {
        this.name = name;
        this.muscleGroups = muscleGroups;
        this.trackingType = trackingType;
        this.user = user;
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

    @Override
    public String toString() {
        return String.format("Exercise{" +
                "id=%d, " +
                "name=%s, " +
                "muscleGroups=%s" +
                "trackingType=%s" +
                "}", id, name, muscleGroups.toString(), trackingType.toString());
    }
}
