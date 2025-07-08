import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "../features/auth/login/LoginPage.tsx";
import RegisterPage from "../features/auth/register/RegisterPage.tsx";
import HomePage from "../features/home/HomePage.tsx";
import PrivateRoutes from "./PrivateRoutes.tsx";
import ExercisePage from "../features/exercise/ExercisePage.tsx";

export default function App() {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    {/* Public Routes */}
                    <Route path="" element={<LoginPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>

                    {/* Private Routes */}
                    <Route element={<PrivateRoutes/>}>
                        <Route path="/home" element={<HomePage/>}/>
                        <Route path="/exercises" element={<ExercisePage/>}/>
                        <Route path="/workouts" element={<p>Workouts Page</p>}/>
                        <Route path="/profile" element={<p>Profile Page</p>}/>
                    </Route>

                    <Route path="*"
                           element={<p>There's nothing here: 404!</p>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}
