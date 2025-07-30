import * as React from "react";
import type { TextFieldProps } from "../../types/form.ts";

export default function TextFieldComponent({
  name,
  label,
  value,
  onChange = () => {},
  onError = () => {},
  placeholder,
  required = false,
}: TextFieldProps) {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    onChange(name, newValue);

    // Validate pattern
    const pattern = /^[a-zA-Z0-9\s\-()]+$/;
    if (newValue && !pattern.test(newValue)) {
      onError(
        name,
        "Only letters, numbers, spaces, hyphens, and parentheses are allowed",
      );
    } else if (required && !newValue.trim()) {
      onError(name, `${label} is required`);
    } else {
      onError(name, null);
    }
  };

  return (
    <div>
      <label className="block mb-2 text-sm font-medium text-gray-700">
        {label}
      </label>
      <input
        type="text"
        name={name}
        value={value || ""}
        onChange={handleChange}
        placeholder={placeholder}
        required={required}
        pattern={"^[a-zA-Z0-9\s\-()]+$"}
        className="px-3 py-2 w-full rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
      />
    </div>
  );
}
