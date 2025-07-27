interface ResourceRowCreateAndDeleteButtonsProps {
  onEdit: () => void;
  onDelete: () => void;
}

export default function RowActionButtons({
  onEdit: handleEdit,
  onDelete: handleDelete,
}: ResourceRowCreateAndDeleteButtonsProps) {
  return (
    <td className="flex flex-row justify-center px-4 py-3">
      <div className="flex gap-2">
        <button
          onClick={handleEdit}
          className="px-3 py-2 text-sm font-medium text-white bg-blue-500 rounded-lg transition-colors hover:bg-blue-600"
        >
          Edit
        </button>
        <button
          onClick={handleDelete}
          className="px-3 py-2 text-sm font-medium text-white bg-red-500 rounded-lg transition-colors hover:bg-red-600"
        >
          Delete
        </button>
      </div>
    </td>
  );
}
