import * as React from "react";
import { useState } from "react";
import type { Exercise } from "./ExercisePage";
import styles from "./Exercise.module.css";

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
    <>
      <h2>{exercise ? "Edit Exercise" : "Create Exercise"}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter exercise name"
            required
          />
        </div>

        <div>
          <label>Description</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter exercise description"
            required
          />
        </div>

        <div className={styles.formActions}>
          <button type="submit" className={styles.submitButton}>
            {exercise ? "Update" : "Create"}
          </button>
          <button
            type="button"
            className={styles.cancelButton}
            onClick={onCancel}
          >
            Cancel
          </button>
        </div>
      </form>
    </>
  );
}
