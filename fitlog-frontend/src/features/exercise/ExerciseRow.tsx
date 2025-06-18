import type { Exercise } from "./ExercisePage.tsx";
import styles from "./Exercise.module.css";

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
        <div className={styles.actionButtons}>
          <button className={styles.editButton} onClick={handleEdit}>
            Edit
          </button>
          <button className={styles.deleteButton} onClick={handleDelete}>
            Delete
          </button>
        </div>
      </td>
    </tr>
  );
}
