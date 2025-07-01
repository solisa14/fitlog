package com.github.solisa14.fitlogbackend.enums;

public enum TrackingType {
    REPS_AND_WEIGHT,      // Sets, reps, weight (bench press, squats)
    TIME_BASED,           // Duration only (treadmill, plank)
    REPS_ONLY,            // Sets, reps (push-ups, pull-ups)
    DISTANCE_AND_DURATION; // Distance, time (running, cycling)

    @Override
    public String toString() {
        String[] splitName = name().toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : splitName) {
            sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
