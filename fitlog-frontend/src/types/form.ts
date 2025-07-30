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

export interface CheckboxOption {
  value: string;
  label: string;
}

export interface CheckboxGroupProps {
  name: string;
  label: string;
  value?: string[];
  onChange?: (name: string, value: string[]) => void;
  options: CheckboxOption[];
}

export interface TextFieldProps {
  name: string;
  label: string;
  value?: string;
  onChange?: (name: string, value: string) => void;
  onError?: (field: string, error: string | null) => void;
  placeholder?: string;
  required?: boolean;
}
