import ExerciseTable from "./ExerciseTable";
import ExerciseForm from "./ExerciseForm";
import ErrorMessage from "../../components/ErrorMessage";
import {useExercises} from "../../hooks/useExercises.ts";
import NavBar from "../../components/NavBar.tsx";

export default function ExercisePage() {
  const {
    exercises,
    exerciseToEdit,
    displayExerciseForm,
    error,
    handleCreateExercise,
    handleUpdateExercise,
    handleDeleteExercise,
    handleEditExercise,
    handleToggleExerciseForm,
  } = useExercises();

  return (
      <div className="flex flex-col w-full h-screen">
        <NavBar/>
        <div className="flex flex-col px-8 py-6 w-full">
          <div
              className="flex flex-row justify-between items-center mb-6 w-full">
            <h1 className="text-2xl font-bold">My Exercises</h1>
            <button
                className="px-3 py-2 text-white bg-red-500 rounded-lg hover:bg-red-600"
                onClick={handleToggleExerciseForm}
            >
              + Create Exercise
            </button>
          </div>

          {error && <ErrorMessage message={error}/>}

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
