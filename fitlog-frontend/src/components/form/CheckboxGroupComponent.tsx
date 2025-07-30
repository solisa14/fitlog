import type { CheckboxGroupProps } from "../../types/form.ts";

export default function CheckboxGroupComponent({
  name,
  label,
  value,
  onChange = () => {},
  options,
}: CheckboxGroupProps) {
  const handleCheckboxChange = (optionValue: string, checked: boolean) => {
    const newValue = checked
      ? [...(value || []), optionValue]
      : (value || []).filter((item) => item !== optionValue);
    onChange(name, newValue);
  };

  return (
    <div>
      <label className="block mb-3 text-sm font-medium text-gray-700">
        {label}
      </label>
      <div className="grid overflow-y-auto grid-cols-2 gap-2 p-3 max-h-40 rounded-lg border border-gray-200">
        {options.map((option) => (
          <div key={option.value} className="flex items-center space-x-2">
            <input
              type="checkbox"
              id={`${name}-${option.value}`}
              checked={(value || []).includes(option.value)}
              onChange={(e) =>
                handleCheckboxChange(option.value, e.target.checked)
              }
              className="w-4 h-4 text-red-500 rounded border-gray-300 focus:ring-red-500 focus:ring-2"
            />
            <label
              htmlFor={`${name}-${option.value}`}
              className="text-sm text-gray-700 cursor-pointer"
            >
              {option.label}
            </label>
          </div>
        ))}
      </div>
    </div>
  );
}
