-- Add timestamp columns to users table
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE users
    ADD COLUMN updated_at TIMESTAMP;

-- Add timestamp columns to exercises table  
ALTER TABLE exercises
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE exercises
    ADD COLUMN updated_at TIMESTAMP;

-- Add timestamp columns to exercise_set table
ALTER TABLE exercise_set
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE exercise_set
    ADD COLUMN updated_at TIMESTAMP;