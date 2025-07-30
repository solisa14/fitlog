import * as React from "react";
import type { ResourceRowProps } from "./ResourcePage.tsx";

interface ResourceTableProps<T> {
  items: T[];
  itemName: string;
  columnNames: string[];
  onEdit: (item: T) => void;
  onDelete: (id: string) => void;
  ResourceRow: React.ComponentType<ResourceRowProps<T>>;
}

export default function ResourceTable<T>({
  items,
  itemName,
  columnNames,
  onEdit,
  onDelete,
  ResourceRow,
}: ResourceTableProps<T>) {
  if (items.length === 0) {
    return (
      <div className="p-8 mt-8 text-center">
        <p className="text-lg text-gray-600">
          {`No ${itemName} found. Create your first ${itemName.toLowerCase()} to get started!`}
        </p>
      </div>
    );
  }

  return (
    <div className="overflow-hidden w-full rounded-lg border border-gray-300">
      <table className="w-full">
        <thead className="bg-gray-100 border-b border-gray-300">
          <tr>
            {columnNames.map((colName, index) => (
              <th
                key={index}
                className="px-4 py-3 font-bold text-left text-gray-700"
              >
                {colName}
              </th>
            ))}
            <th className="px-4 py-3 font-bold text-center text-gray-700">
              Actions
            </th>
          </tr>
        </thead>
        <tbody className="bg-white">
          {items.map((item: T) => (
            <ResourceRow
              key={(item as any).id}
              item={item}
              onEdit={onEdit}
              onDelete={onDelete}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
}
