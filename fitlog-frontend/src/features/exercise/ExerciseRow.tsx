import type { Exercise } from "./ExercisePage.tsx";

interface ExerciseRowProps {
  exercise: Exercise;
  onEdit: (exercise: Exercise) => void;
  onDelete: (id: string) => void;
}

export default function ExerciseRow({
  exercise: { id, name, description },
  onEdit,
  onDelete,
}: ExerciseRowProps) {
  function handleEdit() {
    onEdit({ id, name, description });
  }

  function handleDelete() {
    onDelete(id);
  }

  return (
    <tr id={id}>
      <td>{name}</td>
      <td>{description}</td>
      <td>
        <button onClick={handleEdit}>Edit</button>
      </td>
      <td>
        <button onClick={handleDelete}>Delete</button>
      </td>
    </tr>
  );
}
