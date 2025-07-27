import * as React from "react";
import { useEffect, useState } from "react";

export interface FieldConfig {
  name: string;
  label: string;
  type: "text" | "select" | "checkbox-group" | "custom";
  required?: boolean;
  placeholder?: string;
  options?: Array<{ value: string; label: string }>;
  defaultValue?: any;
  customComponent?: React.ComponentType<{
    value: any;
    onChange: (value: any) => void;
    name: string;
  }>;
}

export interface ResourceFormProps<T> {
  itemToEdit: T | null;
  onCancel: () => void;
  onCreate: (item: T) => void;
  onEdit: (item: T) => void;
  title: string;
  fields: FieldConfig[];
  children?: React.ReactNode;
}

export default function ResourceForm<T extends Record<string, any>>({
  itemToEdit,
  onCancel,
  onCreate,
  onEdit,
  title,
  fields,
  children,
}: ResourceFormProps<T>) {
  const [formData, setFormData] = useState<Record<string, any>>({});

  useEffect(() => {
    if (itemToEdit) {
      setFormData(itemToEdit);
    } else {
      // Initialize with default values
      const initialData: Record<string, any> = {};
      fields.forEach((field) => {
        if (field.defaultValue !== undefined) {
          initialData[field.name] = field.defaultValue;
        } else if (field.type === "checkbox-group") {
          initialData[field.name] = [];
        } else {
          initialData[field.name] = "";
        }
      });
      setFormData(initialData);
    }
  }, [itemToEdit, fields]);

  function handleChange(name: string, value: any): void {
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  }

  function handleInputChange(
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ): void {
    const { name, value } = e.target;
    handleChange(name, value);
  }

  function handleCheckboxGroupChange(
    name: string,
    option: string,
    checked: boolean
  ): void {
    setFormData((prevData) => ({
      ...prevData,
      [name]: checked
        ? [...(prevData[name] || []), option]
        : (prevData[name] || []).filter((item: string) => item !== option),
    }));
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (itemToEdit) {
      onEdit({ ...itemToEdit, ...formData } as T);
    } else {
      onCreate(formData as T);
    }
  }

  function renderField(field: FieldConfig) {
    switch (field.type) {
      case "text":
        return (
          <div key={field.name}>
            <label className="block mb-2 text-sm font-medium text-gray-700">
              {field.label}
            </label>
            <input
              type="text"
              name={field.name}
              value={formData[field.name] || ""}
              onChange={handleInputChange}
              placeholder={field.placeholder}
              required={field.required}
              className="px-3 py-2 w-full rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
            />
          </div>
        );

      case "select":
        return (
          <div key={field.name}>
            <label className="block mb-2 text-sm font-medium text-gray-700">
              {field.label}
            </label>
            <select
              value={formData[field.name] || ""}
              name={field.name}
              onChange={handleInputChange}
              required={field.required}
              className="px-3 py-2 w-full bg-white rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
            >
              {field.options?.map((option, index) => (
                <option key={index} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>
        );

      case "checkbox-group":
        return (
          <div key={field.name}>
            <label className="block mb-3 text-sm font-medium text-gray-700">
              {field.label}
            </label>
            <div className="grid overflow-y-auto grid-cols-2 gap-2 p-3 max-h-40 rounded-lg border border-gray-200">
              {field.options?.map((option) => (
                <div key={option.value} className="flex items-center space-x-2">
                  <input
                    type="checkbox"
                    id={`${field.name}-${option.value}`}
                    checked={(formData[field.name] || []).includes(
                      option.value
                    )}
                    onChange={(e) =>
                      handleCheckboxGroupChange(
                        field.name,
                        option.value,
                        e.target.checked
                      )
                    }
                    className="w-4 h-4 text-red-500 rounded border-gray-300 focus:ring-red-500 focus:ring-2"
                  />
                  <label
                    htmlFor={`${field.name}-${option.value}`}
                    className="text-sm text-gray-700 cursor-pointer"
                  >
                    {option.label}
                  </label>
                </div>
              ))}
            </div>
          </div>
        );

      case "custom":
        return field.customComponent ? (
          <div key={field.name}>
            <field.customComponent
              value={formData[field.name]}
              onChange={(value) => handleChange(field.name, value)}
              name={field.name}
            />
          </div>
        ) : null;

      default:
        return null;
    }
  }

  return (
    <div className="flex fixed inset-0 z-50 justify-center items-center p-4 backdrop-blur-xs">
      <div className="bg-white rounded-lg border border-gray-300 p-6 w-full max-w-md max-h-[90vh] overflow-y-auto">
        <h2 className="mb-6 text-2xl font-bold text-center text-gray-800">
          {itemToEdit ? `Edit ${title}` : `Create ${title}`}
        </h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          {fields.map(renderField)}

          {children}

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
