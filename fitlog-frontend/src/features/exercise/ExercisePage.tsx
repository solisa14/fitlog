import ExerciseTable from "./ExerciseTable";
import ExerciseForm from "./ExerciseForm";
import ErrorMessage from "../../components/ErrorMessage";
import {useExercises} from "../../hooks/useExercises";
import styles from "./Exercise.module.css";

export default function ExercisePage() {
    const {
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
    } = useExercises();

    return (
        <div className={styles.exercisePageContainer}>
            <div className={styles.exercisePageContent}>
                <div className={styles.exercisePageHeader}>
                    <h1>My Exercises</h1>
                    <button
                        className={styles.createButton}
                        onClick={handleToggleExerciseForm}
                        disabled={isLoading}
                    >
                        + Create Exercise
                    </button>
                </div>

                {error && (
                    <ErrorMessage message={error}/>
                )}

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
