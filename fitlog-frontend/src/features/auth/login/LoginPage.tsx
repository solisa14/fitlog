import * as React from "react";
import {useState} from "react";
import {login} from "../../../services/auth-service.ts";
import {Link, useNavigate} from "react-router-dom";
import styles from "./LoginPage.module.css";
import ErrorMessage from "../../../components/ErrorMessage.tsx";

export default function LoginPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    };

    const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await login(email, password);
            // Reset fields after login attempt
            if (response.token && response.refreshToken) {
                navigate("/exercises"); // TODO: change to home page
            } else {
                setErrorMessage("Login failed, please try again.");
            }
            setEmail("");
            setPassword("");
        } catch (error) {
            if (error instanceof Error) {
                setErrorMessage(error.message);
            }
        }
    };

    return (
        <div className={styles.loginPage}>
            <h1>Welcome Back to FitLog</h1>
            <form onSubmit={handleLogin}>
                <input
                    type="email"
                    placeholder="Email"
                    required
                    value={email || ""}
                    onChange={handleEmailChange}
                />
                <input
                    type="password"
                    placeholder="Password"
                    required
                    value={password || ""}
                    onChange={handlePasswordChange}
                />
                {errorMessage && <ErrorMessage message={errorMessage}/>}
                <button type="submit">Login</button>
            </form>
            <Link to="/register" className={styles.registerLink}>
                Don't have an account? Register
            </Link>
        </div>
    );
}
