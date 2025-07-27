import { useEffect, useState } from "react";
import type { Exercise } from "../types/exercise.ts";
import {
  createExercise,
  deleteExercise,
  getExercises,
  updateExercise,
} from "../services/exercise-service.ts";
import type { ResourceHook } from "../components/ResourcePage.tsx";

export function useExercises(): ResourceHook<Exercise> {
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [exerciseToEdit, setExerciseToEdit] = useState<Exercise | null>(null);
  const [displayExerciseForm, setDisplayExerciseForm] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handleCreateExercise(exercise: Exercise): Promise<void> {
    try {
      const createdExercise: Exercise = await createExercise({
        name: exercise.name,
        muscleGroups: exercise.muscleGroups,
        trackingType: exercise.trackingType,
      });
      setExercises((prevExercises) => [...prevExercises, createdExercise]);
      setDisplayExerciseForm(false);
    } catch (error) {
      setError(
        error instanceof Error ? error.message : "Failed to create exercise"
      );
    }
  }

  async function handleGetExercises(): Promise<void> {
    try {
      const fetchedExercises: Exercise[] = await getExercises();
      setExercises(fetchedExercises);
    } catch (error) {
      setError(
        error instanceof Error ? error.message : "Failed to fetch exerc"
      );
    }
  }

  async function handleUpdateExercise(exercise: Exercise): Promise<void> {
    try {
      setDisplayExerciseForm(false);

      const updatedExercise = await updateExercise(exercise, exercise.id);
      setExercises((prev: Exercise[]): Exercise[] =>
        prev.map(
          (ex: Exercise): Exercise =>
            (ex.id === exercise.id ? updatedExercise : ex) as Exercise
        )
      );
    } catch (error) {
      setError(
        error instanceof Error ? error.message : "Failed to update exercise"
      );
    }
  }

  async function handleDeleteExercise(id: string) {
    try {
      await deleteExercise(id);
      setExercises((prev: Exercise[]): Exercise[] =>
        prev.filter((ex: Exercise): boolean => ex.id !== id)
      );
    } catch (error) {
      setError(
        error instanceof Error ? error.message : "Failed to delete exee"
      );
    }
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
    handleGetExercises();
  }, []);

  return {
    items: exercises,
    itemToEdit: exerciseToEdit,
    displayForm: displayExerciseForm,
    error,
    handleCreate: handleCreateExercise,
    handleUpdate: handleUpdateExercise,
    handleDelete: handleDeleteExercise,
    handleEdit: handleEditExercise,
    handleToggleForm: handleToggleExerciseForm,
  };
}
