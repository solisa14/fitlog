import NavBar from "./NavBar.tsx";
import ErrorMessage from "./ErrorMessage.tsx";
import ResourceTable from "./ResourceTable.tsx";
import * as React from "react";
import type { ResourceHook, ResourcePageProps } from "../types/resource.ts";

export default function ResourcePage<T>({
  pageName,
  columnNames,
  resourceName,
  resourceHook,
  ResourceForm,
  ResourceRow,
}: ResourcePageProps<T>) {
  const {
    items,
    itemToEdit,
    displayForm,
    error,
    handleCreate,
    handleUpdate,
    handleDelete,
    handleEdit,
    handleToggleForm,
  }: ResourceHook<T> = resourceHook;

  return (
    <div className="flex flex-col w-full h-screen">
      <NavBar />
      <div className="flex flex-col px-8 py-6 w-full">
        <div className="flex flex-row justify-between items-center mb-6 w-full">
          <h1 className="text-2xl font-bold">{pageName}</h1>
          <button
            className="px-3 py-2 text-white bg-red-500 rounded-lg hover:bg-red-600"
            onClick={handleToggleForm}
          >
            {`+ Create ${resourceName}`}
          </button>
        </div>

        {error && <ErrorMessage message={error} />}

        <ResourceTable
          items={items}
          itemName={resourceName}
          columnNames={columnNames}
          onEdit={handleEdit}
          onDelete={handleDelete}
          ResourceRow={ResourceRow}
        />
        {displayForm && (
          <div>
            <div>
              <ResourceForm
                itemToEdit={itemToEdit}
                onCancel={handleToggleForm}
                onCreate={handleCreate}
                onEdit={handleUpdate}
              />
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
