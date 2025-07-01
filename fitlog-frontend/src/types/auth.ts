export interface AuthResponse {
    email: string;
    password: string;
    token: string;
}

export interface ErrorResponse {
    type: string;
    detail: string;
    status: number;
    title: string;
    instance: string;
} 