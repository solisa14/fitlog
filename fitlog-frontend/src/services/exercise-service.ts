import type { Exercise, ExerciseRequest } from "../types/exercise";
import { apiRequest } from "./api";
import { getAuthToken } from "./auth-service";

const BASE_URL: string = "http://localhost:8080/api/v1/exercises";

export async function createExercise(
  exerciseRequest: ExerciseRequest
): Promise<Exercise> {
  try {
    const token = getAuthTokenAndValidate();
    const response: Exercise = await apiRequest<Exercise>(
      BASE_URL,
      {
        method: "POST",
        body: JSON.stringify(exerciseRequest),
      },
      token
    );
    return response;
  } catch (error) {
    console.error("Error creating exercise:", error);
    throw error;
  }
}

export async function getExercises(): Promise<Exercise[]> {
  try {
    const token = getAuthTokenAndValidate();
    const response: Exercise[] = await apiRequest<Exercise[]>(
      BASE_URL,
      {
        method: "GET",
      },
      token
    );
    return response;
  } catch (error) {
    console.error("Error getting exercises:", error);
    throw error;
  }
}

export async function updateExercise(
  exerciseRequest: ExerciseRequest
): Promise<Exercise> {
  try {
    const token = getAuthTokenAndValidate();
    const response: Exercise = await apiRequest<Exercise>(
      BASE_URL,
      {
        method: "PUT",
        body: JSON.stringify(exerciseRequest),
      },
      token
    );
    return response;
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

function getAuthTokenAndValidate(): string {
  const token = getAuthToken();
  if (!token) {
    throw new Error("No auth token found");
  }
  return token;
}
