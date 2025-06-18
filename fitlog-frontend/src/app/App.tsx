import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "../features/auth/login/LoginPage.tsx";
import RegisterPage from "../features/auth/register/RegisterPage.tsx";
import HomePage from "../features/home/HomePage.tsx";
import PrivateRoutes from "./PrivateRoutes.tsx";
import styles from './App.module.css';

function App() {
    return (
        <div className={styles.page}>
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
                        <Route path="/dashboard" element={<HomePage/>}/>
                        <Route path="/workouts" element={<p>Workouts Page</p>}/>
                        <Route path="/profile" element={<p>Profile Page</p>}/>
                    </Route>

                    <Route path="*" element={
                        <p>There's nothing here: 404!</p>
                    }/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App
