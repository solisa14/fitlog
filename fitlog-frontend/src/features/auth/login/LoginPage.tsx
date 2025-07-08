import {useNavigate} from "react-router-dom";
import {login} from "../../../services/auth-service.ts";
import AuthForm from "../../../components/AuthForm";

export default function LoginPage() {
    const navigate = useNavigate();

    const handleLogin = async (
        email: string,
        password: string
    ): Promise<void> => {
        const response = await login(email, password);
        if (response.token) {
            navigate("/home");
        } else {
            throw new Error("Login failed, please try again.");
        }
    };

    return (
        <AuthForm
            title="Welcome Back to FitLog"
            buttonText="Login"
            linkText="Don't have an account? Register"
            linkTo="/register"
            onSubmit={handleLogin}
        />
    );
}
