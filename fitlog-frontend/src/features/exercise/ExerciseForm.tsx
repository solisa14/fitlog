import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
  TrackingType,
} from "../../types/enum.ts";
import type { Exercise } from "../../types/exercise.ts";
import type { ResourceFormProps } from "../../components/ResourcePage.tsx";
import ResourceForm from "../../components/ResourceForm.tsx";
import TextFieldComponent from "../../components/form/TextFieldComponent.tsx";
import CheckboxGroupComponent from "../../components/form/CheckboxGroupComponent.tsx";
import SelectFieldComponent from "../../components/form/SelectFieldComponent.tsx";

export default function ExerciseForm({
  itemToEdit: exercise,
  onCancel,
  onCreate,
  onEdit,
}: ResourceFormProps<Exercise>) {
  const muscleGroupOptions = Object.values(MuscleGroup).map((muscleGroup) => ({
    value: muscleGroup,
    label: getMuscleGroupDisplayName(muscleGroup),
  }));

  const trackingTypeOptions = Object.values(TrackingType).map((type) => ({
    value: type,
    label: getTrackingTypeDisplayName(type),
  }));

  return (
    <ResourceForm
      itemToEdit={exercise}
      onCancel={onCancel}
      onCreate={onCreate}
      onEdit={onEdit}
      title="Exercise"
      initialData={{
        name: "",
        muscleGroups: [],
        trackingType: TrackingType.REPS_AND_WEIGHT,
      }}
    >
      {({ formData, handleChange, setError }) => {
        const handleMuscleGroupChange = (name: string, value: string[]) => {
          handleChange(name, value);
          if (value.length === 0) {
            setError(name, "At least one muscle group is required");
          } else {
            setError(name, null);
          }
        };

        return (
          <>
            <TextFieldComponent
              name="name"
              label="Exercise Name"
              placeholder="Enter exercise name"
              required
              value={formData.name}
              onChange={handleChange}
              onError={setError}
            />
            <CheckboxGroupComponent
              name="muscleGroups"
              label="Muscle Groups"
              options={muscleGroupOptions}
              value={formData.muscleGroups}
              onChange={handleMuscleGroupChange}
            />
            <SelectFieldComponent
              name="trackingType"
              label="Tracking Type"
              options={trackingTypeOptions}
              required
              value={formData.trackingType}
              onChange={handleChange}
            />
          </>
        );
      }}
    </ResourceForm>
  );
}
