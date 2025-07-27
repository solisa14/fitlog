import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
  TrackingType,
} from "../../types/enum.ts";
import type { Exercise } from "../../types/exercise.ts";
import type { ResourceFormProps } from "../../components/ResourcePage.tsx";
import ResourceForm, {
  type FieldConfig,
} from "../../components/ResourceForm.tsx";

// TODO: make it so that the exercise form create or update button is disabled if the muscle groups is empty or if the tracking type is not selected
// TODO: add more strict input handling adhering to the rules set in the backend
export default function ExerciseForm({
  itemToEdit: exercise,
  onCancel,
  onCreate,
  onEdit,
}: ResourceFormProps<Exercise>) {
  const fields: FieldConfig[] = [
    {
      name: "name",
      label: "Exercise Name",
      type: "text",
      required: true,
      placeholder: "Enter exercise name",
    },
    {
      name: "muscleGroups",
      label: "Muscle Groups",
      type: "checkbox-group",
      options: Object.values(MuscleGroup).map((muscleGroup) => ({
        value: muscleGroup,
        label: getMuscleGroupDisplayName(muscleGroup),
      })),
      defaultValue: [],
    },
    {
      name: "trackingType",
      label: "Tracking Type",
      type: "select",
      required: true,
      options: Object.values(TrackingType).map((type) => ({
        value: type,
        label: getTrackingTypeDisplayName(type),
      })),
      defaultValue: TrackingType.REPS_AND_WEIGHT,
    },
  ];

  return (
    <ResourceForm
      itemToEdit={exercise}
      onCancel={onCancel}
      onCreate={onCreate}
      onEdit={onEdit}
      title="Exercise"
      fields={fields}
    />
  );
}
