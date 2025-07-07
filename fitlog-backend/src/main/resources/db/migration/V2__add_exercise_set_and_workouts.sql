-- Create workouts table
CREATE TABLE workouts
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    user_id    BIGINT       NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    CONSTRAINT fk_workouts_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Add workout_id to exercise_set table
ALTER TABLE exercise_set
    ADD COLUMN workout_id BIGINT;
ALTER TABLE exercise_set
    ADD CONSTRAINT fk_exercise_set_workout
        FOREIGN KEY (workout_id) REFERENCES workouts (id) ON DELETE CASCADE;