export interface AuthResponse {
    email: string;
    password: string;
    token: string;
    refreshToken: string;
}

export interface ErrorResponse {
    type: string;
    detail: string;
    status: number;
    title: string;
    instance: string;
} 