import {getAuthToken} from "./auth-service.ts";
import type {ExerciseRequest, ExerciseResponse} from '../types/exercise.ts';
import {makeAuthenticatedRequest, processApiResponse} from './api-helpers';

const BASE_URL: string = "http://localhost:8080/api/v1/exercises";

export async function createExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    const response = await makeAuthenticatedRequest(BASE_URL, {
        method: "POST",
        body: JSON.stringify(exerciseRequest),
    }, token);

    return processApiResponse<ExerciseResponse>(response);
}

export async function getExercises(): Promise<ExerciseResponse[]> {
    const token = getAuthTokenAndValidate();

    const response = await makeAuthenticatedRequest(BASE_URL, {
        method: "GET",
    }, token);

    return processApiResponse<ExerciseResponse[]>(response);
}

export async function updateExercise(
    exerciseRequest: ExerciseRequest
): Promise<ExerciseResponse> {
    const token = getAuthTokenAndValidate();

    const response = await makeAuthenticatedRequest(`${BASE_URL}/${exerciseRequest.id}`, {
        method: "PUT",
        body: JSON.stringify(exerciseRequest),
    }, token);

    return processApiResponse<ExerciseResponse>(response);
}

export async function deleteExercise(id: string): Promise<void> {
    const token = getAuthTokenAndValidate();

    const response = await makeAuthenticatedRequest(`${BASE_URL}/${id}`, {
        method: "DELETE",
    }, token);

    if (!response.ok) {
        try {
            const errorData = await response.json();
            throw new Error(errorData.detail || "Failed to delete exercise");
        } catch {
            throw new Error("Failed to delete exercise");
        }
    }
}

function getAuthTokenAndValidate(): string {
    const token = getAuthToken();
    if (!token) {
        throw new Error("No auth token found");
    }
    return token;
}
