import * as React from "react";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {register} from "../../../services/AuthService.ts";
import styles from './RegisterPage.module.css';


export default function RegisterPage() {
    const navigate = useNavigate()
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleRegister = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = await register(email, password);
        if (data.token && data.refreshToken) {
            // Registration successful, redirect to home
            navigate('/home');
        } else {
            // Handle registration failure (e.g., show an error message)
            alert('Registration failed, please try again.');
            console.error('Registration failed');
        }
        // Reset fields after registration attempt
        setEmail('');
        setPassword('');
    }

    return (
        <div className={styles.registerPage}>
            <h1>Welcome to FitLog</h1>
            <form onSubmit={handleRegister}>
                <input type="email" placeholder="Email" required value={email}
                       onChange={handleEmailChange}/>
                <input type="password" placeholder="Password" required
                       value={password} onChange={handlePasswordChange}/>
                <button type="submit">Register</button>
            </form>
            <a href="/login">Already have an account? Login</a>
        </div>
    );
}