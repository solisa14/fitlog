import {type ChangeEvent, type FormEvent, useState} from "react";
import {Link} from "react-router-dom";
import ErrorMessage from "./ErrorMessage";

interface AuthFormProps {
    title: string;
    buttonText: string;
    linkText: string;
    linkTo: string;
    onSubmit: (email: string, password: string) => Promise<void>;
}

export default function AuthForm({
                                     title,
                                     buttonText,
                                     linkText,
                                     linkTo,
                                     onSubmit,
                                 }: AuthFormProps) {
    const [form, setForm] = useState({email: "", password: ""});
    const [errorMessage, setErrorMessage] = useState("");
    
    const handleChange = (e: ChangeEvent<HTMLInputElement>): void => {
        const {name, value} = e.target;
        setForm(prevForm => ({...prevForm, [name]: value}));
    };

    const handleSubmit = async (e: FormEvent<HTMLFormElement>): Promise<void> => {
        e.preventDefault();
        try {
            setErrorMessage("");
            await onSubmit(form.email, form.password);
            setForm({email: "", password: ""});
        } catch (error) {
            if (error instanceof Error) {
                setErrorMessage(error.message);
            } else {
                setErrorMessage("An unexpected error occurred");
            }
        }
    };

    return (
        <div>
            <h1>{title}</h1>
            <form onSubmit={handleSubmit}>
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
                <button
                    type="submit"
                >
                    {buttonText}
                </button>
            </form>
            <Link to={linkTo}>
                {linkText}
            </Link>
        </div>
    );
}
