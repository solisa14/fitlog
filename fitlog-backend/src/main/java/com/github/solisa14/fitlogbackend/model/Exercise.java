package com.github.solisa14.fitlogbackend.model;

import jakarta.persistence.*;

/**
 * Represents an exercise entity in the application.
 * Each exercise is associated with a user and has a name and description.
 */
@Entity(name = "exercise")
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // Specifies the foreign key column in the exercises table
    private User user;

    public Exercise() {
    }

    public Exercise(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Exercise{id=%d, name=%s, description=%s}",
                id, name, description);
    }
}
