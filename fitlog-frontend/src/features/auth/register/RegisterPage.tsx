import * as React from "react";
import {type ChangeEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {type AuthResponse, register} from "../../../services/auth-service.ts";
import styles from "./RegisterPage.module.css";
import ErrorMessage from "../../../components/ErrorMessage.tsx";

export default function RegisterPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({email: "", password: ""});
    const [errorMessage, setErrorMessage] = useState("");

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setForm(prev => ({...prev, [name]: value}));
    }

    const handleRegister = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response: AuthResponse = await register(form.email, form.password);
            if (response.token && response.refreshToken) {
                navigate("/exercises"); // TODO: change to home page later
            } else {
                setErrorMessage("Registration failed, please try again.");
            }
            setForm({email: "", password: ""});
        } catch (error) {
            if (error instanceof Error) {
                setErrorMessage(error.message);
            }
        }
    };

    return (
        <div className={styles.registerPage}>
            <h1>Welcome to FitLog</h1>
            <form onSubmit={handleRegister}>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    required
                    value={form.email}
                    onChange={handleChange}
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    required
                    value={form.password}
                    onChange={handleChange}
                />
                {errorMessage && <ErrorMessage message={errorMessage}/>}
                <button type="submit">Register</button>
            </form>
            <Link to="/login" className={styles.loginLink}>
                Already have an account? Login
            </Link>
        </div>
    );
}
