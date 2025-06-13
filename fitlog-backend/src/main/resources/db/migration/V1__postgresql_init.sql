-- Create users table
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL
);

-- Create exercises table
CREATE TABLE exercises
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    user_id     BIGINT NOT NULL,
    CONSTRAINT fk_exercise_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Create refresh_tokens table
CREATE TABLE refresh_tokens
(
    id          BIGSERIAL PRIMARY KEY,
    token       VARCHAR(255) UNIQUE NOT NULL,
    expiry_date TIMESTAMP           NOT NULL,
    user_id     BIGINT              NOT NULL,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_exercises_user_id ON exercises (user_id);
CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens (user_id);
CREATE INDEX idx_refresh_tokens_token ON refresh_tokens (token);
CREATE INDEX idx_refresh_tokens_expiry_date ON refresh_tokens (expiry_date);
