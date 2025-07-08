import {getAuthToken} from "../services/auth-service.ts";
import {Navigate, Outlet} from "react-router-dom";

export default function PrivateRoutes() {
    const token: string | null = getAuthToken();

    return token ? <Outlet/> : <Navigate to="/login" replace/>;
}
