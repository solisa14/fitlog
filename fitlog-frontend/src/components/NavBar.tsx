import { Link, useNavigate } from "react-router-dom";
import { logout } from "../services/auth-service";

export default function NavBar() {
  const navigate = useNavigate();

  function handleLogout() {
    logout();
    navigate("/login");
  }

  return (
    <nav className="flex flex-row justify-between items-center px-8 py-4 w-full border-b border-gray-300">
      <h1 className="text-2xl font-extrabold">Fitlog</h1>
      <div className="flex flex-row gap-3">
        <Link
          className="px-3 py-2 text-sm font-medium text-gray-600 rounded-lg hover:bg-gray-300 hover:text-gray-800"
          to="/"
        >
          Home
        </Link>
        <Link
          className="px-3 py-2 text-sm font-medium text-gray-600 rounded-lg hover:bg-gray-300 hover:text-gray-800"
          to="/exercises"
        >
          Exercises
        </Link>
        <Link
          className="px-3 py-2 text-sm font-medium text-gray-600 rounded-lg hover:bg-gray-300 hover:text-gray-800"
          to="/workouts"
        >
          Workouts
        </Link>
        <Link
          className="px-3 py-2 text-sm font-medium text-gray-600 rounded-lg hover:bg-gray-300 hover:text-gray-800"
          to="/profile"
        >
          Profile
        </Link>
      </div>
      <button
        className="px-3 py-2 text-white bg-red-500 rounded-lg hover:bg-red-600"
        onClick={handleLogout}
      >
        Logout
      </button>
    </nav>
  );
}
