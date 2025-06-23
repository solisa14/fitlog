import {getAuthToken} from "./auth-service.ts";
import type {
    MuscleGroup,
    TrackingType
} from "../features/exercise/ExercisePage.tsx";

export interface ExerciseRequest {
    id: number | string;
    name: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
}

export interface ExerciseResponse {
    id: number | string;
    name: string;
    muscleGroups: MuscleGroup[];
    trackingType: TrackingType;
}

const BASE_URL: string = "http://localhost:8080/api/v1/exercises";

export async function createExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(exerciseRequest),
    });

    if (!response.ok) {
        throw new Error("Failed to create exercise");
    }

    return await response.json();
}

export async function getExercises(): Promise<ExerciseResponse[]> {
    const token = getAuthTokenAndValidate();

    const response = await fetch(BASE_URL, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });

    if (!response.ok) {
        throw new Error("Failed to get exercises");
    }

    return await response.json();
}

export async function updateExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    const response = await fetch(`${BASE_URL}/${exerciseRequest.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(exerciseRequest),
    });

    if (!response.ok) {
        console.log(response);
        throw new Error("Failed to update exercise");
    }

    return await response.json();
}

export async function deleteExercise(id: string) {
    const token = getAuthTokenAndValidate();

    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    if (!response.ok) {
        throw new Error("Failed to delete exercise");
    }
}

function getAuthTokenAndValidate(): string {
    const token = getAuthToken();
    if (!token) {
        throw new Error("No auth token found");
    }
    return token;
}
