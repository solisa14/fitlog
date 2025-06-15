import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "../features/auth/login/LoginPage.tsx";
import RegisterPage from "../features/auth/register/RegisterPage.tsx";
import HomePage from "../features/home/HomePage.tsx";
import PrivateRoutes from "./PrivateRoutes.tsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                {/* Public Routes */}
                <Route path="" element={<LoginPage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register"
                       element={<RegisterPage/>}/>

                {/* Private Routes */}
                <Route element={<PrivateRoutes/>}>
                    <Route path="/home" element={<HomePage/>}/>
                </Route>

                <Route path="*" element={
                    <p>There's nothing here: 404!</p>
                }/>
            </Routes>
        </BrowserRouter>
    )
}

export default App
