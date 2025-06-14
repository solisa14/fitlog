interface AuthResponse {
    email: string;
    password: string;
    token: string;
    refreshToken: string;
}

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

export function getIsAuthenticated(): boolean {
    const token = getAuthToken();
    return token !== null;
}

export async function login(email: string, password: string): Promise<AuthResponse> {
    const response = await fetch(`${BASE_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({email, password}),
    });

    if (!response.ok) {
        throw new Error('Login failed');
    }
    const data = await response.json();

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

    if (!response.ok) {
        throw new Error('Registration failed');
    }
    const data = await response.json();

    setAuthToken(data.token);
    setRefreshToken(data.refreshToken);

    return data;
}