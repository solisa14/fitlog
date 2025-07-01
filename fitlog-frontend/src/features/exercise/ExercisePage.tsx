import ExerciseTable from "./ExerciseTable";
import ExerciseForm from "./ExerciseForm";
import ErrorMessage from "../../components/ErrorMessage";
import {useExercises} from "../../hooks/useExercises";

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
        <div>
            <div>
                <div>
                    <h1>My Exercises</h1>
                    <button
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
                    <div>
                        <div>
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
