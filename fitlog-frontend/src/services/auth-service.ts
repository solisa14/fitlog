import type {AuthResponse} from '../types/auth.ts';
import {processApiResponse} from './api-helpers';

const BASE_URL: string = 'http://localhost:8080/api/v1/auth'

export function getAuthToken(): string | null {
    return sessionStorage.getItem('authToken');
}

function setAuthToken(token: string): void {
    sessionStorage.setItem('authToken', token);
}

function setRefreshToken(token: string): void {
    sessionStorage.setItem('refreshToken', token);
}

export function isAuthenticated(): boolean {
    const token = getAuthToken();
    return token !== null;
}

export async function login(email: string, password: string): Promise<AuthResponse> {
    const response = await fetch(`${BASE_URL}/login`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password}),
    });

    const data = await processApiResponse<AuthResponse>(response);

    setAuthToken(data.token);
    setRefreshToken(data.refreshToken);
    return data;
}

export async function register(email: string, password: string): Promise<AuthResponse> {
    const response = await fetch(`${BASE_URL}/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({email, password}),
    });

    const data = await processApiResponse<AuthResponse>(response);

    setAuthToken(data.token);
    setRefreshToken(data.refreshToken);
    return data;
}