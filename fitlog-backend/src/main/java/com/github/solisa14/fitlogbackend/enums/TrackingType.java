package com.github.solisa14.fitlogbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TrackingType {
    REPS_AND_WEIGHT("reps_and_weight"),
    TIME_BASED("time_based"),
    REPS_ONLY("reps_only"),
    DISTANCE_AND_DURATION("distance_and_duration");

    private final String name;

    TrackingType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static TrackingType fromJson(String name) {
        for (TrackingType trackingType : TrackingType.values()) {
            if (trackingType.name.equals(name)) {
                return trackingType;
            }
        }
        throw new IllegalArgumentException("Invalid tracking type: " + name);
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public String toJson() {
        return this.name;
    }

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
