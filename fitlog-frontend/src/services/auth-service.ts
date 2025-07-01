import type { AuthResponse } from "../types/auth";
import { apiRequest, HttpError } from "./api";

const BASE_URL: string = "http://localhost:8080/api/v1/auth";

export async function login(
  email: string,
  password: string
): Promise<AuthResponse> {
  try {
    const response = await apiRequest<AuthResponse>(`${BASE_URL}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    setAuthToken(response.token);
    return response;
  } catch (error) {
    if (error instanceof HttpError) {
      throw new Error(error.message);
    }
    throw error;
  }
}

export async function register(
  email: string,
  password: string
): Promise<AuthResponse> {
  try {
    const response = await apiRequest<AuthResponse>(`${BASE_URL}/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    setAuthToken(response.token);
    return response;
  } catch (error) {
    if (error instanceof HttpError) {
      throw new Error(error.message);
    }
    throw error;
  }
}

export function getAuthToken(): string | null {
  return sessionStorage.getItem("authToken");
}

function setAuthToken(token: string): void {
  sessionStorage.setItem("authToken", token);
}
