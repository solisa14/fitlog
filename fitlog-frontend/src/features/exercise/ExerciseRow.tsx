import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
} from "../../types/enum.ts";
import type {Exercise} from "../../types/exercise.ts";

interface ExerciseRowProps {
  exercise: Exercise;
  onEdit: (exercise: Exercise) => void;
  onDelete: (id: string) => void;
}

export default function ExerciseRow({
  exercise,
  onEdit,
  onDelete,
}: ExerciseRowProps) {
  const { id, name, muscleGroups, trackingType } = exercise;

  function handleEdit() {
    onEdit({ id, name, muscleGroups, trackingType });
  }

  function handleDelete() {
    onDelete(id);
  }

  return (
    <tr id={id} className="border-b border-gray-200 hover:bg-gray-50">
      <td className="px-4 py-3 font-medium text-left">{name}</td>
      <td className="px-4 py-3 text-left">
        <div>
          {muscleGroups && muscleGroups.length > 0
            ? muscleGroups
                .map((muscleGroup: MuscleGroup) =>
                  getMuscleGroupDisplayName(muscleGroup)
                )
                .join(", ")
            : "None"}
        </div>
      </td>
      <td className="px-4 py-3 text-left">
        {getTrackingTypeDisplayName(trackingType)}
      </td>
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
    </tr>
  );
}
