import {useEffect, useState} from "react";
import {
    createExercise,
    deleteExercise,
    getExercises,
    updateExercise,
} from "../services/exercise-service";
import type {Exercise} from "../types/exercise.ts";

export function useExercises() {
    const [exercises, setExercises] = useState<Exercise[]>([]);
    const [exerciseToEdit, setExerciseToEdit] = useState<Exercise | null>(null);
    const [displayExerciseForm, setDisplayExerciseForm] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    async function handleCreateExercise(exercise: Exercise) {
        try {
            setIsLoading(true);
            setError(null);
            setDisplayExerciseForm(false);

            const createdExercise = await createExercise({
                id: "",
                name: exercise.name,
                muscleGroups: exercise.muscleGroups,
                trackingType: exercise.trackingType
            });
            setExercises(prev => [...prev, createdExercise as Exercise]);
        } catch (error) {
            setError(error instanceof Error ? error.message : "Failed to create exercise");
        } finally {
            setIsLoading(false);
        }
    }

    async function fetchExercises() {
        try {
            setIsLoading(true);
            setError(null);

            const fetchedExercises = await getExercises();
            setExercises(fetchedExercises as Exercise[]);
        } catch (error) {
            setError(error instanceof Error ? error.message : "Failed to fetch exercises");
        } finally {
            setIsLoading(false);
        }
    }

    async function handleUpdateExercise(exercise: Exercise) {
        try {
            setIsLoading(true);
            setError(null);
            setDisplayExerciseForm(false);

            const updatedExercise = await updateExercise(exercise);
            setExercises((prev: Exercise[]): Exercise[] =>
                prev.map((e: Exercise): Exercise => (e.id === exercise.id ? updatedExercise : e) as Exercise)
            );
        } catch (error) {
            setError(error instanceof Error ? error.message : "Failed to update exercise");
        } finally {
            setIsLoading(false);
        }
    }

    async function handleDeleteExercise(id: string) {
        try {
            setIsLoading(true);
            setError(null);

            await deleteExercise(id);
            setExercises(prev => prev.filter(e => e.id !== id));
        } catch (error) {
            setError(error instanceof Error ? error.message : "Failed to delete exercise");
        } finally {
            setIsLoading(false);
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
        fetchExercises();
    }, []);

    return {
        exercises,
        exerciseToEdit,
        displayExerciseForm,
        isLoading,
        error,
        handleCreateExercise,
        handleUpdateExercise,
        handleDeleteExercise,
        handleEditExercise,
        handleToggleExerciseForm,
        refetchExercises: fetchExercises,
    };
} 