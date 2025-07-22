import {getAuthTokenAndValidate} from "./auth-service.ts";
import type {Workout, WorkoutRequest} from "../types/workout.ts";
import {apiRequest} from "./api.ts";


const BASE_URL: string = "http://localhost:8080/api/v1/workouts";

export async function getAllWorkouts(): Promise<Workout[]> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Workout[]>(
            BASE_URL,
            {
                method: "GET",
            },
            token
        );
    } catch (error) {
        console.error("Error getting workouts:", error);
        throw error;
    }
}

export async function getWorkoutById(id: string): Promise<Workout> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Workout>(
            `${BASE_URL}/${id}`,
            {
                method: "GET",
            },
            token
        );
    } catch (error) {
        console.error("Error getting workout:", error);
        throw error;
    }
}

export async function createWorkout(
    workoutRequest: WorkoutRequest
): Promise<Workout> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Workout>(
            BASE_URL,
            {
                method: "POST",
                body: JSON.stringify(workoutRequest),
            },
            token
        );
    } catch (error) {
        console.error("Error creating workout:", error);
        throw error;
    }
}

export async function updateWorkout(
    workoutRequest: WorkoutRequest,
    id: string
): Promise<Workout> {
    try {
        const token = getAuthTokenAndValidate();
        return await apiRequest<Workout>(
            `${BASE_URL}/${id}`,
            {
                method: "PUT",
                body: JSON.stringify(workoutRequest),
            },
            token
        );
    } catch (error) {
        console.error("Error updating workout:", error);
        throw error;
    }
}

export async function deleteWorkout(id: string): Promise<void> {
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
        console.error("Error deleting workout:", error);
        throw error;
    }
}
