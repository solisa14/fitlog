import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import LoginPage from "../features/auth/login/LoginPage.tsx";
import RegisterPage from "../features/auth/register/RegisterPage.tsx";
import * as React from "react";
import {getAuthToken} from "../services/AuthService.ts";
import HomePage from "../features/home/HomePage.tsx";

// Protected Route component
const ProtectedRoute = ({children}: { children: React.ReactNode }) => {
    const token = getAuthToken();
    if (!token) {
        return <Navigate to="/login" replace/>;
    }
    return <>{children}</>;
};

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="" element={<LoginPage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register"
                       element={<RegisterPage/>}/>
                <Route path="/home" element={
                    <ProtectedRoute>
                        <HomePage/>
                    </ProtectedRoute>
                }/>
                <Route path="*" element={
                    <p>There's nothing here: 404!</p>
                }/>
            </Routes>
        </BrowserRouter>
    )
}

export default App
