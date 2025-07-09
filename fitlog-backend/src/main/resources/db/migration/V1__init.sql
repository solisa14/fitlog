CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE exercises
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    tracking_type VARCHAR(255) NOT NULL,
    user_id       BIGINT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE exercise_muscle_groups
(
    exercise_id   BIGINT,
    muscle_groups VARCHAR(255),
    FOREIGN KEY (exercise_id) REFERENCES exercises (id)
);

CREATE TABLE workouts
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    user_id    BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE exercise_set
(
    id          BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT,
    workout_id  BIGINT,
    logged_at   TIMESTAMP,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    set_number  INT,
    reps        INT,
    weight      DOUBLE PRECISION,
    rpe         DOUBLE PRECISION,
    duration    NUMERIC(21, 0),
    distance    DOUBLE PRECISION,
    FOREIGN KEY (exercise_id) REFERENCES exercises (id),
    FOREIGN KEY (workout_id) REFERENCES workouts (id)
);
