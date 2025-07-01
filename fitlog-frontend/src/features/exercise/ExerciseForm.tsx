import * as React from "react";
import { useEffect, useState } from "react";
import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
  TrackingType,
} from "../../types/enum.ts";
import type { Exercise } from "../../types/exercise.ts";

interface ExerciseFormProps {
  exercise: Exercise | null;
  onCancel: () => void;
  onCreate: (exercise: Exercise) => void;
  onEdit: (exercise: Exercise) => void;
}

interface ExerciseFormData {
  name: string;
  muscleGroups: MuscleGroup[];
  trackingType: TrackingType;
}

export default function ExerciseForm({
  exercise,
  onCancel,
  onCreate,
  onEdit,
}: ExerciseFormProps) {
  const [formData, setFormData] = useState<ExerciseFormData>({
    name: "",
    muscleGroups: [],
    trackingType: TrackingType.REPS_AND_WEIGHT, // Default to REPS_AND_WEIGHT
  });

  useEffect(() => {
    if (exercise) {
      setFormData({
        name: exercise.name,
        muscleGroups: exercise.muscleGroups,
        trackingType: exercise.trackingType,
      });
    }
  }, [exercise]);

  function handleChange(
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ): void {
    const { name, value } = e.target;
    setFormData((prevData: ExerciseFormData) => ({
      ...prevData,
      [name]: value,
    }));
  }

  function handleMuscleGroupChange(muscleGroup: MuscleGroup, checked: boolean) {
    setFormData((prevData: ExerciseFormData) => ({
      ...prevData,
      muscleGroups: checked
        ? [...prevData.muscleGroups, muscleGroup]
        : prevData.muscleGroups.filter((mg: MuscleGroup) => mg !== muscleGroup),
    }));
  }
  

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (exercise) {
      onEdit({ id: exercise.id, ...formData });
    } else {
      onCreate(formData as Exercise);
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
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Enter exercise name"
            required
          />
        </div>

        <div>
          <label>Muscle Groups</label>
          <div>
            {Object.values(MuscleGroup).map((muscleGroup: MuscleGroup) => (
              <div key={muscleGroup}>
                <input
                  type="checkbox"
                  id={muscleGroup}
                  checked={formData.muscleGroups.includes(muscleGroup)}
                  onChange={(e) =>
                    handleMuscleGroupChange(muscleGroup, e.target.checked)
                  }
                />
                <label htmlFor={muscleGroup}>
                  {getMuscleGroupDisplayName(muscleGroup)}
                </label>
              </div>
            ))}
          </div>
        </div>

        <div>
          <label>Tracking Type</label>
          <select
            value={formData.trackingType || ""}
            name="trackingType"
            onChange={handleChange}
            required
          >
            {Object.values(TrackingType).map(
              (type: TrackingType, index: number) => (
                <option key={index} value={type}>
                  {getTrackingTypeDisplayName(type)}
                </option>
              )
            )}
          </select>
        </div>

        <div>
          <button type="submit">{exercise ? "Update" : "Create"}</button>
          <button type="button" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </form>
    </>
  );
}
