import {type ChangeEvent, type FormEvent, useState} from "react";
import {login} from "../../../services/auth-service.ts";
import {Link, useNavigate} from "react-router-dom";
import styles from "./LoginPage.module.css";
import ErrorMessage from "../../../components/ErrorMessage.tsx";

export default function LoginPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({email: "", password: ""});
    const [errorMessage, setErrorMessage] = useState("");

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setForm(prev => ({...prev, [name]: value}));
    };

    const handleLogin = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await login(form.email, form.password);
            if (response.token && response.refreshToken) {
                navigate("/exercises"); // TODO: change to home page
            } else {
                setErrorMessage("Login failed, please try again.");
            }
            setForm({email: "", password: ""});
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
                <button type="submit">Login</button>
            </form>
            <Link to="/register" className={styles.registerLink}>
                Don&apos;t have an account? Register
            </Link>
        </div>
    );
}