import * as React from "react";
import {useState} from "react";
import {login} from "../../../services/AuthService.ts";
import {useNavigate} from "react-router-dom";
import styles from './LoginPage.module.css';


export default function LoginPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const response = await login(email, password);
        // Reset fields after login attempt
        if (response.token && response.refreshToken) {
            navigate('/home');
        } else {
            alert('Login failed, please try again.');
            console.error('Login failed');
            // Handle login failure (e.g., show an error message)
        }
        setEmail('');
        setPassword('');
    }

    return (
        <div className={styles.loginPage}>
            <h1>Welcome Back to FitLog</h1>
            <form onSubmit={handleLogin}>
                <input type="email" placeholder="Email" required
                       value={email || ''} onChange={handleEmailChange}/>
                <input type="password" placeholder="Password" required
                       value={password || ''} onChange={handlePasswordChange}/>
                <button type="submit">Login</button>
            </form>
            <a href="/register">Don't have an account? Register</a>
        </div>
    )
}