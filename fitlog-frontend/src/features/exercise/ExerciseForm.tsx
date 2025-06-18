import { useState } from "react";
import type { Exercise } from "./ExercisePage";

interface ExerciseFormProps {
  exercise: Exercise | null;
  onCancel: () => void;
  onCreate: (exercise: Exercise) => void;
  onEdit: (exercise: Exercise) => void;
}

export default function ExerciseForm({
  exercise,
  onCancel,
  onCreate,
  onEdit,
}: ExerciseFormProps) {
  const [name, setName] = useState(exercise?.name || "");
  const [description, setDescription] = useState(exercise?.description || "");

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (exercise) {
      onEdit({ id: exercise.id, name, description });
    } else {
      onCreate({ id: "", name, description });
    }
  }

  return (
    <div>
      <h2>{exercise ? "Edit Exercise" : "Create Exercise"}</h2>
      <form onSubmit={handleSubmit}>
        <label>Name</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <label>Description</label>
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <button type="submit">{exercise ? "Update" : "Create"}</button>
        <button type="button" onClick={onCancel}>
          Cancel
        </button>
      </form>
    </div>
  );
}
