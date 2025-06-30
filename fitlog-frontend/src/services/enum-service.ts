import type {MuscleGroup, TrackingType} from "../types/enum.ts";

export async function fetchMuscleGroups(): Promise<MuscleGroup[]> {
    const response = await fetch("http://localhost:8080/api/v1/muscle-groups");
    if (!response.ok) {
        throw new Error("Failed to fetch muscle groups");
    }
    return response.json();
}

export async function fetchTrackingTypes(): Promise<TrackingType[]> {
    const response = await fetch("http://localhost:8080/api/v1/tracking-types");
    if (!response.ok) {
        throw new Error("Failed to fetch tracking types");
    }
    return response.json();
}