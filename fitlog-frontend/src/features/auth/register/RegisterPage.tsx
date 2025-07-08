import {useNavigate} from "react-router-dom";
import {register} from "../../../services/auth-service.ts";
import AuthForm from "../../../components/AuthForm";

export default function RegisterPage() {
    const navigate = useNavigate();

    const handleRegister = async (
        email: string,
        password: string
    ): Promise<void> => {
        const response = await register(email, password);
        if (response.token) {
            navigate("/home");
        } else {
            throw new Error("Registration failed, please try again.");
        }
    };

    return (
        <AuthForm
            title="Welcome to FitLog"
            buttonText="Register"
            linkText="Already have an account? Login"
            linkTo="/login"
            onSubmit={handleRegister}
        />
    );
}
