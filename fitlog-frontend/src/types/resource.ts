import * as React from "react";

export interface ResourceHook<T> {
  items: T[];
  itemToEdit: T | null;
  displayForm: boolean;
  error: string | null;
  handleCreate: (item: T) => void;
  handleUpdate: (item: T) => void;
  handleDelete: (id: string) => void;
  handleEdit: (item: T) => void;
  handleToggleForm: () => void;
}

export interface ResourcePageProps<T> {
  pageName: string;
  columnNames: string[];
  resourceName: string;
  resourceHook: ResourceHook<T>;
  ResourceForm: React.ComponentType<ResourceFormProps<T>>;
  ResourceRow: React.ComponentType<ResourceRowProps<T>>;
}

export interface ResourceFormProps<T> {
  itemToEdit: T | null;
  onCancel: () => void;
  onCreate: (item: T) => void;
  onEdit: (item: T) => void;
}

export interface ResourceRowProps<T> {
  item: T;
  onEdit: (item: T) => void;
  onDelete: (id: string) => void;
}
