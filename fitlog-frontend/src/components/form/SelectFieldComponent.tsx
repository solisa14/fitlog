import * as React from "react";

export interface SelectOption {
  value: string;
  label: string;
}

export interface SelectFieldProps {
  name: string;
  label: string;
  value?: string;
  onChange?: (name: string, value: string) => void;
  options: SelectOption[];
  required?: boolean;
}

export default function SelectFieldComponent({
  name,
  label,
  value,
  onChange = () => {},
  options,
  required = false,
}: SelectFieldProps) {
  const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    onChange(name, e.target.value);
  };

  return (
    <div>
      <label className="block mb-2 text-sm font-medium text-gray-700">
        {label}
      </label>
      <select
        value={value || ""}
        name={name}
        onChange={handleChange}
        required={required}
        className="px-3 py-2 w-full bg-white rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
      >
        {options.map((option, index) => (
          <option key={index} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
}
