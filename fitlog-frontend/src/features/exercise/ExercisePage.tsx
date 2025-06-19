import { useEffect, useState } from "react";
import {
  createExercise,
  deleteExercise,
  getExercises,
  updateExercise,
} from "../../services/exercise-service";
import ExerciseTable from "./ExerciseTable";
import ExerciseForm from "./ExerciseForm";
import styles from "./Exercise.module.css";

export interface Exercise {
  id: string;
  name: string;
  description: string;
}

export default function ExercisePage() {
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [exerciseToEdit, setExerciseToEdit] = useState<Exercise | null>(null);
  const [displayExerciseForm, setDisplayExerciseForm] = useState(false);

  async function handleCreateExercise(exercise: Exercise) {
    setDisplayExerciseForm(false);
    const createdExercise = await createExercise({
      id: "",
      name: exercise.name,
      description: exercise.description,
    });
    setExercises([...exercises, createdExercise]);
  }

  async function fetchExercises() {
    const fetchedExercises = await getExercises();
    setExercises(fetchedExercises);
  }

  async function handleUpdateExercise(exercise: Exercise) {
    setDisplayExerciseForm(false);
    const updatedExercise = await updateExercise({
      id: exercise.id,
      name: exercise.name,
      description: exercise.description,
    });
    setExercises(
      exercises.map((e) => (e.id === exercise.id ? updatedExercise : e))
    );
  }

  async function handleDeleteExercise(id: string) {
    await deleteExercise(id);
    setExercises(exercises.filter((e) => e.id !== id));
  }

  function handleEditExercise(exercise: Exercise) {
    setExerciseToEdit(exercise);
    setDisplayExerciseForm(true);
  }

  function handleToggleExerciseForm() {
    setDisplayExerciseForm(!displayExerciseForm);
    setExerciseToEdit(null);
  }

  useEffect(() => {
    fetchExercises();
  }, []);

  return (
    <div className={styles.exercisePageContainer}>
      <div className={styles.exercisePageContent}>
        <div className={styles.exercisePageHeader}>
          <h1>My Exercises</h1>
          <button
            className={styles.createButton}
            onClick={handleToggleExerciseForm}
          >
            + Create Exercise
          </button>
        </div>
        <ExerciseTable
          exercises={exercises}
          onEdit={handleEditExercise}
          onDelete={handleDeleteExercise}
        />
        {displayExerciseForm && (
          <div className={styles.formOverlay}>
            <div className={styles.formContainer}>
              <ExerciseForm
                exercise={exerciseToEdit}
                onCancel={handleToggleExerciseForm}
                onCreate={handleCreateExercise}
                onEdit={handleUpdateExercise}
              />
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
