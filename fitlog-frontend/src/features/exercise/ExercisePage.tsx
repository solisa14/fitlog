import { useExercises } from "../../hooks/useExercises.ts";
import ResourcePage from "../../components/ResourcePage.tsx";
import type { Exercise } from "../../types/exercise.ts";
import ExerciseForm from "./ExerciseForm.tsx";
import ExerciseRow from "./ExerciseRow.tsx";
import type { ResourceHook } from "../../types/resource.ts";

export default function ExercisePage() {
  const exerciseHook: ResourceHook<Exercise> = useExercises();

  return (
    <ResourcePage<Exercise>
      pageName={"My Exercises"}
      columnNames={["Name", "Muscle Groups", "Tracking Type"]}
      resourceName={"Exercise"}
      resourceHook={exerciseHook}
      ResourceForm={ExerciseForm}
      ResourceRow={ExerciseRow}
    />
  );
}
