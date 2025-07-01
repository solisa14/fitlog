package com.github.solisa14.fitlogbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MuscleGroup {
    // Push
    CHEST("chest"),
    SHOULDERS("shoulders"),
    TRICEPS("triceps"),
    // Pull
    BACK("back"),
    LATS("lats"),
    BICEPS("biceps"),
    REAR_DELTS("rear_delts"),
    // Core
    ABS("abs"),
    OBLIQUES("obliques"),
    LOWER_BACK("lower_back"),
    // Lower
    QUADRICEPS("quadriceps"),
    HAMSTRINGS("hamstrings"),
    GLUTES("glutes"),
    CALVES("calves"),
    HIP_FLEXORS("hip_flexors"),
    // Compound/Full Body
    FULL_BODY("full_body"),
    // Specific Areas
    FOREARMS("forearms"),
    TRAPS("traps"),
    NECK("neck"),
    CARDIO("cardio"),
    // Functional Groups
    HIP_ABDUCTORS("hip_abductors"),
    HIP_ADDUCTORS("hip_adductors"),
    ROTATOR_CUFF("rotator_cuff");

    private final String name;

    MuscleGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    public String toJson() {
        return this.name;
    }

    @JsonCreator
    public static MuscleGroup fromJson(String name) {
        for (MuscleGroup muscleGroup : MuscleGroup.values()) {
            if (muscleGroup.name.equals(name)) {
                return muscleGroup;
            }
        }
        throw new IllegalArgumentException("Invalid muscle group: " + name);
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
