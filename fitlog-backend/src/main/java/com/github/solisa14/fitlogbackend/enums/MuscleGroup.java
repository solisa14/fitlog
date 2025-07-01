package com.github.solisa14.fitlogbackend.enums;

public enum MuscleGroup {
    CHEST,
    BACK,
    SHOULDERS,
    ARMS,
    LEGS,
    CORE,
    GLUTES,
    FULL_BODY;

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
