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
      <div
          className="flex flex-col justify-center items-center min-h-screen bg-white">
          <h1 className="mb-10 text-5xl font-bold text-gray-900">{title}</h1>
      <form
        className="flex flex-col gap-4 px-4 mb-5 w-full max-w-md"
        onSubmit={handleSubmit}
      >
        <input
            className="p-2 text-gray-900 bg-gray-200 rounded-xl border-2 border-gray-500 placeholder:text-gray-500 focus:outline-none focus:ring-2 focus:ring-gray-700"
          type="email"
          name="email"
          placeholder="Email"
          required
          value={form.email}
          onChange={handleChange}
        />
        <input
            className="p-2 text-gray-900 bg-gray-200 rounded-xl border-2 border-gray-500 placeholder:text-gray-500 focus:outline-none focus:ring-2 focus:ring-gray-700"
          type="password"
          name="password"
          placeholder="Password"
          required
          value={form.password}
          onChange={handleChange}
        />
        {errorMessage && <ErrorMessage message={errorMessage} />}
        <button
            className="p-2 text-white bg-red-500 rounded-full hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-300"
          type="submit"
        >
          {buttonText}
        </button>
      </form>
      <Link
          className="text-red-500 hover:text-red-800 hover:underline"
        to={linkTo}
      >
        {linkText}
      </Link>
    </div>
  );
}
