import type {Exercise, ExerciseRequest} from "../types/exercise";
import {apiRequest} from "./api";
import {getAuthTokenAndValidate} from "./auth-service";

const BASE_URL: string = "http://localhost:8080/api/v1/exercises";

export async function createExercise(
    exerciseRequest: ExerciseRequest
): Promise<Exercise> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Exercise>(
            BASE_URL,
            {
                method: "POST",
                body: JSON.stringify(exerciseRequest),
            },
            token
        );
    } catch (error) {
        console.error("Error creating exercise:", error);
        throw error;
    }
}

export async function getExercises(): Promise<Exercise[]> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Exercise[]>(
            BASE_URL,
            {
                method: "GET",
            },
            token
        );
    } catch (error) {
        console.error("Error getting exercises:", error);
        throw error;
    }
}

export async function updateExercise(
    exerciseRequest: ExerciseRequest,
    id: string
): Promise<Exercise> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Exercise>(
            `${BASE_URL}/${id}`,
            {
                method: "PUT",
                body: JSON.stringify(exerciseRequest),
            },
            token
        );
    } catch (error) {
        console.error("Error updating exercise:", error);
        throw error;
    }
}

export async function deleteExercise(id: string): Promise<void> {
    try {
        const token = getAuthTokenAndValidate();
        await apiRequest<void>(
            `${BASE_URL}/${id}`,
            {
                method: "DELETE",
            },
            token
        );
    } catch (error) {
        console.error("Error deleting exercise:", error);
        throw error;
    }
}

