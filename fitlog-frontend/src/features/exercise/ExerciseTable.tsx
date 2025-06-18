import type { Exercise } from "./ExercisePage.tsx";
import ExerciseRow from "./ExerciseRow.tsx";

interface ExerciseTableProps {
  exercises: Exercise[];
  onEdit: (exercise: Exercise) => void;
  onDelete: (id: string) => void;
}

export default function ExerciseTable({
  exercises,
  onEdit,
  onDelete,
}: ExerciseTableProps) {
  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Description</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {exercises.map((exercise: Exercise) => (
          <ExerciseRow
            key={exercise.id}
            exercise={exercise}
            onEdit={onEdit}
            onDelete={onDelete}
          />
        ))}
      </tbody>
    </table>
  );
}
