import {type MuscleGroup, TrackingType} from "./enum.ts";

export interface ExerciseSet {
    id: string,
    exerciseId: string;
    exerciseName: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
    loggedAt: string;
    setNumber: number;
    reps: number;
    weight: number;
    rpe: number;
    duration: string;
    distance: number;
}

export interface Workout {
    id: string,
    name: string;
    exerciseSets: ExerciseSet[];
}

export interface WorkoutRequest {
    name: string;
    exerciseSets?: ExerciseSet[];
}
