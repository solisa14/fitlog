-- Initial database schema for FitLog application

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create exercises table
CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tracking_type VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_exercises_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create exercise_muscle_groups table for the ElementCollection
CREATE TABLE exercise_muscle_groups (
    exercise_id BIGINT NOT NULL,
    muscle_groups VARCHAR(50) NOT NULL,
    PRIMARY KEY (exercise_id, muscle_groups),
    CONSTRAINT fk_exercise_muscle_groups_exercise FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_exercises_user_id ON exercises(user_id);
CREATE INDEX idx_exercise_muscle_groups_exercise_id ON exercise_muscle_groups(exercise_id);
