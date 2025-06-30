import type {ErrorResponse} from '../types/auth.ts';

/**
 * Centralized function to process API responses and handle errors consistently
 */
export async function processApiResponse<T>(response: Response): Promise<T> {
    let data: T | ErrorResponse;

    try {
        data = await response.json();
    } catch (error) {
        console.error(error);
        throw new Error('Failed to parse response from server');
    }

    if (!response.ok) {
        const errorData = data as ErrorResponse;
        switch (response.status) {
            case 400:
                throw new Error(errorData.detail || 'Bad request');
            case 401:
                throw new Error(errorData.detail || 'Unauthorized: Invalid credentials');
            case 403:
                throw new Error(errorData.detail || 'Forbidden: Access denied');
            case 404:
                throw new Error(errorData.detail || 'Resource not found');
            case 409:
                throw new Error(errorData.detail || 'Conflict: Resource already exists');
            case 500:
                throw new Error(errorData.detail || 'Internal server error');
            default:
                throw new Error(errorData.detail || 'An unexpected error occurred');
        }
    }

    return data as T;
}

/**
 * Helper function for making authenticated API requests
 */
export async function makeAuthenticatedRequest(
    url: string,
    options: RequestInit,
    authToken: string
): Promise<Response> {
    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`,
        ...options.headers,
    };

    return fetch(url, {
        ...options,
        headers,
    });
} 