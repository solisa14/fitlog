import * as React from "react";
import { useEffect, useState } from "react";
import ErrorMessage from "./ErrorMessage.tsx";

export interface ResourceFormProps<T> {
  itemToEdit: T | null;
  onCancel: () => void;
  onCreate: (item: T) => void;
  onEdit: (item: T) => void;
  title: string;
  initialData?: Record<string, any>;
  children: (helpers: {
    formData: Record<string, any>;
    handleChange: (name: string, value: any) => void;
    setError: (field: string, error: string | null) => void;
    errors: Record<string, any>;
  }) => React.ReactNode;
}

export default function ResourceForm<T extends Record<string, any>>({
  itemToEdit,
  onCancel,
  onCreate,
  onEdit,
  title,
  initialData = {},
  children,
}: ResourceFormProps<T>) {
  const [formData, setFormData] = useState<Record<string, any>>({});
  const [errors, setErrors] = useState<Record<string, any>>({});

  useEffect(() => {
    if (itemToEdit) {
      setFormData(itemToEdit);
    } else {
      setFormData(initialData);
    }
  }, [itemToEdit, initialData]); // Re-run when itemToEdit or initialData changes

  function handleChange(name: string, value: any): void {
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

    if (errors[name]) {
      setError(name, null);
    }
  }

  function setError(field: string, error: string | null): void {
    setErrors((prevErrors) => ({
      ...prevErrors,
      [field]: error,
    }));
  }

  /**
   * Form submission handler
   * Determines whether to create a new item or update an existing one based on itemToEdit
   *
   * @param e - Form submission event
   */
  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const hasErrors = Object.values(errors).some((error) => error !== null);
    if (hasErrors) {
      return; // Prevent submission if there are validation errors
    }

    if (itemToEdit) {
      onEdit({ ...itemToEdit, ...formData } as T);
    } else {
      onCreate(formData as T);
    }
  }

  // Get the first error message to display
  const firstError = Object.values(errors).find((error) => error !== null);

  return (
    <div className="flex fixed inset-0 z-50 justify-center items-center p-4 backdrop-blur-xs">
      <div className="bg-white rounded-lg border border-gray-300 p-6 w-full max-w-md max-h-[90vh] overflow-y-auto">
        <h2 className="mb-6 text-2xl font-bold text-center text-gray-800">
          {itemToEdit ? `Edit ${title}` : `Create ${title}`}
        </h2>

        {firstError && <ErrorMessage message={firstError || null} />}
        <form onSubmit={handleSubmit} className="space-y-6">
          {/* Render children as a function, passing form state and handlers */}
          {children({ formData, handleChange, setError, errors })}

          {/* Form action buttons */}
          <div className="flex gap-3 pt-4">
            <button
              type="submit"
              className="flex-1 px-3 py-2 font-medium text-white bg-red-500 rounded-lg transition-colors hover:bg-red-600"
            >
              {itemToEdit ? `Update ${title}` : `Create ${title}`}
            </button>
            <button
              type="button"
              onClick={onCancel}
              className="flex-1 px-3 py-2 font-medium text-gray-700 bg-gray-200 rounded-lg transition-colors hover:bg-gray-300"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
