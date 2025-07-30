import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
} from "../../types/enum.ts";
import RowActionButtons from "../../components/RowActionButtons.tsx";
import type { ResourceRowProps } from "../../types/resource.ts";
import type { Exercise } from "../../types/exercise.ts";

export default function ExerciseRow({
  item: exercise,
  onEdit,
  onDelete,
}: ResourceRowProps<Exercise>) {
  const { id, name, muscleGroups, trackingType } = exercise;

  function handleEdit() {
    onEdit({ id, name, muscleGroups, trackingType });
  }

  function handleDelete() {
    onDelete(id);
  }

  return (
    <tr
      id={`exercise-${id}`}
      className="border-b border-gray-200 hover:bg-gray-50"
    >
      <td className="px-4 py-3 font-medium text-left">{name}</td>
      <td className="px-4 py-3 text-left">
        <div>
          {muscleGroups && muscleGroups.length > 0
            ? muscleGroups
                .map((muscleGroup: MuscleGroup) =>
                  getMuscleGroupDisplayName(muscleGroup),
                )
                .join(", ")
            : "None"}
        </div>
      </td>
      <td className="px-4 py-3 text-left">
        {getTrackingTypeDisplayName(trackingType)}
      </td>
      <RowActionButtons onDelete={handleDelete} onEdit={handleEdit} />
    </tr>
  );
}
