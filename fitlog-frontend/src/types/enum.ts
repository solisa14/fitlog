export enum MuscleGroup {
  // Push
  CHEST = "chest",
  SHOULDERS = "shoulders",
  TRICEPS = "triceps",

  // Pull
  BACK = "back",
  LATS = "lats",
  BICEPS = "biceps",
  REAR_DELTS = "rear_delts",

  // Core
  ABS = "abs",
  OBLIQUES = "obliques",
  LOWER_BACK = "lower_back",

  // Lower
  QUADRICEPS = "quadriceps",
  HAMSTRINGS = "hamstrings",
  GLUTES = "glutes",
  CALVES = "calves",
  HIP_FLEXORS = "hip_flexors",

  // Compound/Full Body
  FULL_BODY = "full_body",

  // Specific Areas
  FOREARMS = "forearms",
  TRAPS = "traps",
  NECK = "neck",
  CARDIO = "cardio",

  // Functional Groups
  HIP_ABDUCTORS = "hip_abductors",
  HIP_ADDUCTORS = "hip_adductors",
  ROTATOR_CUFF = "rotator_cuff",
}

export const MuscleGroupDisplayNames = {
  [MuscleGroup.CHEST]: "Chest",
  [MuscleGroup.SHOULDERS]: "Shoulders",
  [MuscleGroup.TRICEPS]: "Triceps",
  [MuscleGroup.BACK]: "Back",
  [MuscleGroup.LATS]: "Lats",
  [MuscleGroup.BICEPS]: "Biceps",
  [MuscleGroup.REAR_DELTS]: "Rear Delts",
  [MuscleGroup.ABS]: "Abs",
  [MuscleGroup.OBLIQUES]: "Obliques",
  [MuscleGroup.LOWER_BACK]: "Lower Back",
  [MuscleGroup.QUADRICEPS]: "Quadriceps",
  [MuscleGroup.HAMSTRINGS]: "Hamstrings",
  [MuscleGroup.GLUTES]: "Glutes",
  [MuscleGroup.CALVES]: "Calves",
  [MuscleGroup.HIP_FLEXORS]: "Hip Flexors",
  [MuscleGroup.FULL_BODY]: "Full Body",
  [MuscleGroup.FOREARMS]: "Forearms",
  [MuscleGroup.TRAPS]: "Traps",
  [MuscleGroup.NECK]: "Neck",
  [MuscleGroup.CARDIO]: "Cardio",
  [MuscleGroup.HIP_ABDUCTORS]: "Hip Abductors",
  [MuscleGroup.HIP_ADDUCTORS]: "Hip Adductors",
  [MuscleGroup.ROTATOR_CUFF]: "Rotator Cuff",
} as const;

export function getMuscleGroupDisplayName(muscleGroup: MuscleGroup): string {
  return MuscleGroupDisplayNames[muscleGroup];
}

export function isValidMuscleGroup(
  muscleGroup: string,
): muscleGroup is MuscleGroup {
  return Object.values(MuscleGroup).includes(muscleGroup as MuscleGroup);
}

export enum TrackingType {
  REPS_AND_WEIGHT = "reps_and_weight",
  TIME_BASED = "time_based",
  REPS_ONLY = "reps_only",
  DISTANCE_AND_DURATION = "distance_and_duration",
}

export const TrackingTypeDisplayNames = {
  [TrackingType.REPS_AND_WEIGHT]: "Reps and Weight",
  [TrackingType.TIME_BASED]: "Time Based",
  [TrackingType.REPS_ONLY]: "Reps Only",
  [TrackingType.DISTANCE_AND_DURATION]: "Distance and Duration",
} as const;

export function getTrackingTypeDisplayName(trackingType: TrackingType): string {
  return TrackingTypeDisplayNames[trackingType];
}

export function isValidTrackingType(
  trackingType: string,
): trackingType is TrackingType {
  return Object.values(TrackingType).includes(trackingType as TrackingType);
}
