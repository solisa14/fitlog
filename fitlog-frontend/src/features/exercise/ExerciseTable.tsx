import type { Exercise } from "../../types/exercise.ts";
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
  if (exercises.length === 0) {
    return (
      <div className="p-8 mt-8 text-center">
        <p className="text-lg text-gray-600">
          No exercises found. Create your first exercise to get started!
        </p>
      </div>
    );
  }

  return (
    <div className="overflow-hidden mt-6 w-full rounded-xl border-2 border-gray-500">
      <table className="w-full">
        <thead className="bg-gray-100 border-b-2 border-gray-500">
          <tr>
            <th className="px-4 py-3 font-bold text-left text-gray-700">
              Name
            </th>
            <th className="px-4 py-3 font-bold text-left text-gray-700">
              Muscle Groups
            </th>
            <th className="px-4 py-3 font-bold text-left text-gray-700">
              Tracking Type
            </th>
            <th className="px-4 py-3 font-bold text-center text-gray-700">
              Actions
            </th>
          </tr>
        </thead>
        <tbody className="bg-white">
          {exercises.map((exercise: Exercise, index: number) => (
            <ExerciseRow
              key={index}
              exercise={exercise}
              onEdit={onEdit}
              onDelete={onDelete}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
}
