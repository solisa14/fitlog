import {getAuthToken} from "./auth-service.ts";
import type {
    MuscleGroup,
    TrackingType
} from "../features/exercise/ExercisePage.tsx";

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

const BASE_URL: string = "http://localhost:8080/api/v1/exercises";

export async function createExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    try {
        const response = await fetch(BASE_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(exerciseRequest),
        });
        return await response.json();
    } catch (error) {
        if (error instanceof Error) {
            throw new Error(error.message || "Failed to create exercise");
        }
        throw new Error("Unexpected error occurred");
    }
}

export async function getExercises(): Promise<ExerciseResponse[]> {
    const token = getAuthTokenAndValidate();
    try {
        const response = await fetch(BASE_URL, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        });
        return await response.json();
    } catch (error) {
        if (error instanceof Error) {
            throw new Error(error.message || "Failed to fetch exercises");
        }
        throw new Error("Unexpected error occurred");
    }
}

export async function updateExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    try {
        const response = await fetch(`${BASE_URL}/${exerciseRequest.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(exerciseRequest),
        });

        return await response.json();
    } catch (error) {
        if (error instanceof Error) {
            throw new Error(error.message || "Failed to update exercise");
        }
        throw new Error("Unexpected error occurred");
    }
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
