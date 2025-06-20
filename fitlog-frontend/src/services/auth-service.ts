interface AuthResponse {
    email: string;
    password: string;
    token: string;
    refreshToken: string;
}

interface ErrorResponse {
    type: string;
    detail: string;
    status: number;
    title: string;
    instance: string;
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

    let data: AuthResponse | ErrorResponse;
    try {
        data = await response.json();
    } catch (error) {
        throw new Error('Failed to parse response from server');
    }

    if (!response.ok) {
        switch (response.status) {
            case 400:
                throw new Error((data as ErrorResponse).detail || 'Invalid email format');
            case 401:
                throw new Error((data as ErrorResponse).detail || 'Unauthorized: Invalid credentials');
            case 500:
                throw new Error((data as ErrorResponse).detail || 'Internal server error');
            default:
                throw new Error((data as ErrorResponse).detail || 'An unexpected error occurred');
        }
    }

    // success path
    setAuthToken((data as AuthResponse).token);
    setRefreshToken((data as AuthResponse).refreshToken);
    return data as AuthResponse;
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