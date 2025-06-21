package com.github.solisa14.fitlogbackend.model;

public enum TrackingType {
    REPS_AND_WEIGHT,      // Sets, reps, weight (bench press, squats)
    TIME_BASED,           // Duration only (treadmill, plank)
    REPS_ONLY,            // Sets, reps (push-ups, pull-ups)
    DISTANCE_AND_DURATION // Distance, time (running, cycling)
}
