import { type ChangeEvent, type FormEvent, useState } from "react";
import { Link } from "react-router-dom";
import ErrorMessage from "./ErrorMessage";

interface AuthFormProps {
  title: string;
  buttonText: string;
  linkText: string;
  linkTo: string;
  onSubmit: (email: string, password: string) => Promise<void>;
}

interface AuthFormData {
  email: string;
  password: string;
}

export default function AuthForm({
  title,
  buttonText,
  linkText,
  linkTo,
  onSubmit,
}: AuthFormProps) {
  const [form, setForm] = useState<AuthFormData>({ email: "", password: "" });
  const [errorMessage, setErrorMessage] = useState<string>("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>): Promise<void> => {
    e.preventDefault();
    try {
      setErrorMessage("");
      await onSubmit(form.email, form.password);
      setForm({ email: "", password: "" });
    } catch (error) {
      if (error instanceof Error) {
        setErrorMessage(error.message);
      } else {
        setErrorMessage("An unexpected error occurred");
      }
    }
  };

  return (
    <div className="flex flex-col bg-white dark:bg-gray-900 items-center justify-center min-h-screen transition-colors duration-300">
      <h1 className="text-5xl font-bold mb-10 text-gray-900 dark:text-white transition-colors duration-300">
        {title}
      </h1>
      <form
        className="flex flex-col gap-4 mb-5 w-full max-w-md px-4"
        onSubmit={handleSubmit}
      >
        <input
          className="bg-gray-200 dark:bg-gray-800 placeholder:text-gray-500 dark:placeholder:text-gray-400 border-2 border-gray-500 dark:border-gray-400 rounded-xl p-2 focus:outline-none focus:ring-2 focus:ring-gray-700 dark:focus:ring-gray-300 text-gray-900 dark:text-white transition-colors duration-200"
          type="email"
          name="email"
          placeholder="Email"
          required
          value={form.email}
          onChange={handleChange}
        />
        <input
          className="bg-gray-200 dark:bg-gray-800 placeholder:text-gray-500 dark:placeholder:text-gray-400 border-2 border-gray-500 dark:border-gray-400 rounded-xl p-2 focus:outline-none focus:ring-2 focus:ring-gray-700 dark:focus:ring-gray-300 text-gray-900 dark:text-white transition-colors duration-200"
          type="password"
          name="password"
          placeholder="Password"
          required
          value={form.password}
          onChange={handleChange}
        />
        {errorMessage && <ErrorMessage message={errorMessage} />}
        <button
          className="bg-red-500 hover:bg-red-700 dark:bg-red-700 dark:hover:bg-red-900 text-white rounded-full p-2 focus:outline-none focus:ring-2 focus:ring-red-300 dark:focus:ring-red-800 transition-colors duration-200"
          type="submit"
        >
          {buttonText}
        </button>
      </form>
      <Link
        className="text-red-500 hover:text-red-800 dark:text-red-400 dark:hover:text-red-200 hover:underline transition-colors duration-200"
        to={linkTo}
      >
        {linkText}
      </Link>
    </div>
  );
}
