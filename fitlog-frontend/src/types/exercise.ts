import type { MuscleGroup, TrackingType } from "./enum";

export interface Exercise {
  id: string;
  name: string;
  muscleGroups: MuscleGroup[];
  trackingType: TrackingType;
}

export interface ExerciseRequest {
  name: string;
  muscleGroups: MuscleGroup[];
  trackingType: TrackingType;
}
