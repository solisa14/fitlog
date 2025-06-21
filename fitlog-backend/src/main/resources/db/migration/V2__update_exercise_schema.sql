-- Remove description column from exercises table
ALTER TABLE exercises DROP COLUMN description;

-- Add tracking_type column to exercises table
ALTER TABLE exercises
    ADD COLUMN tracking_type VARCHAR(255) NOT NULL DEFAULT 'REPS_AND_WEIGHT';

-- Create exercise_muscle_groups table for the many-to-many relationship
CREATE TABLE exercise_muscle_groups
(
    exercise_id   BIGINT       NOT NULL,
    muscle_groups VARCHAR(255) NOT NULL,
    CONSTRAINT fk_exercise_muscle_groups_exercise
        FOREIGN KEY (exercise_id) REFERENCES exercises (id) ON DELETE CASCADE
);

-- Create index for better performance
CREATE INDEX idx_exercise_muscle_groups_exercise_id ON exercise_muscle_groups (exercise_id);