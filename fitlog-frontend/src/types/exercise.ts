export type MuscleGroup =
    | "CHEST"
    | "BACK"
    | "SHOULDERS"
    | "ARMS"
    | "LEGS"
    | "CORE"
    | "GLUTES"
    | "FULL_BODY";

export type TrackingType =
    | "REPS_AND_WEIGHT"
    | "TIME_BASED"
    | "REPS_ONLY"
    | "DISTANCE_AND_DURATION";

export interface Exercise {
    id: string;
    name: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
}

export interface ExerciseRequest {
    id: string;
    name: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
}

export interface ExerciseResponse {
    id: string;
    name: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
} 