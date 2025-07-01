import { useEffect } from "react";
import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
} from "../../types/enum.ts";
import type { Exercise } from "../../types/exercise.ts";

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
    <tr id={id}>
      <td>{name}</td>
      <td>
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
      <td>{getTrackingTypeDisplayName(trackingType)}</td>
      <td>
        <div>
          <button onClick={handleEdit}>Edit</button>
          <button onClick={handleDelete}>Delete</button>
        </div>
      </td>
    </tr>
  );
}
