import * as React from "react";
import { useEffect, useState } from "react";
import {
  getMuscleGroupDisplayName,
  getTrackingTypeDisplayName,
  MuscleGroup,
  TrackingType,
} from "../../types/enum.ts";
import type { Exercise } from "../../types/exercise.ts";
import type { ResourceFormProps } from "../../components/ResourcePage.tsx";

interface ExerciseFormData {
  name: string;
  muscleGroups: MuscleGroup[];
  trackingType: TrackingType;
}

// TODO: make it so that the exercise form create or update button is disabled if the muscle groups is empty or if the tracking type is not selected
// TODO: add more strict input handling adhering to the rules set in the backend
export default function ExerciseForm({
  itemToEdit: exercise,
  onCancel,
  onCreate,
  onEdit,
}: ResourceFormProps<Exercise>) {
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
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>,
  ): void {
    const { name, value } = e.target;
    setormData((prevData: ExerciseFormData) => ({
      ...prevData,
      [name]: value,
    }));
  }

  function handleMuscleGroupChange(muscleGroup: MuscleGroup, checked: boolean) {
    setFormData((prevData: ExerciseFormData) => ({
      ...prevData,
      muscleGroups: checked
        ? [...prevData.muscleGroups, muscleGroup]
        : prevData.muscleGroups.filter((mg: MuscleGroup) => mg !== muscleGrou),
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
    <div className="flex fixed inset-0 z-50 justify-center items-center p-4 backdrop-blur-xs">
      <div className="bg-white rounded-lg border border-gray-300 p-6 w-full max-w-md max-h-[90vh] overflow-y-auto">
        <h2 className="mb-6 text-2xl font-bold text-center text-gray-800">
          {exercise ? "Edit Exercise" : "Create Exercise"}
        </h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block mb-2 text-sm font-medium text-gray-700">
              Exercise Name
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              placeholder="Enter exercise name"
              required
              className="px-3 py-2 w-full rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
            />
          </div>

          <div>
            <label className="block mb-3 text-sm font-medium text-gray-700">
              Muscle Groups
            </label>
            <div className="grid overflow-y-auto grid-cols-2 gap-2 p-3 max-h-40 rounded-lg border border-gray-200">
              {Object.values(MuscleGroup).map((muscleGroup: MuscleGroup) => (
                <div key={muscleGroup} className="flex items-center space-x-2">
                  <input
                    type="checkbox"
                    id={muscleGroup}
                    checked={formData.muscleGroups.includes(muscleGroup)}
                    onChange={(e) =>
                      handleMuscleGroupChange(muscleGroup, e.target.checked)
                    }
                    className="w-4 h-4 text-red-500 rounded border-gray-300 focus:ring-red-500 focus:ring-2"
                  />
                  <label
                    htmlFor={muscleGroup}
                    className="text-sm text-gray-700 cursor-pointer"
                  >
                    {getMuscleGroupDisplayName(muscleGroup)}
                  </label>
                </div>
              ))}
            </div>
          </div>

          <div>
            <label className="block mb-2 text-sm font-medium text-gray-700">
              Tracking Type
            </label>
            <select
              value={formData.trackingType || ""}
              name="trackingType"
              onChange={handleChange}
              required
              className="px-3 py-2 w-full bg-white rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent"
            >
              {Object.values(TrackingType).map(
                (type: TrackingType, index: number) => (
                  <option key={index} value={type}>
                    {getTrackingTypeDisplayName(type)}
                  </option>
                ),
              )}
            </select>
          </div>

          <div className="flex gap-3 pt-4">
            <button
              type="submit"
              className="flex-1 px-3 py-2 font-medium text-white bg-red-500 rounded-lg transition-colors hover:bg-red-600"
            >
              {exercise ? "Update Exercise" : "Create Exercise"}
            </button>
            <button
              type="button"
              onClick={onCancel}
              className="flex-1 px-3 py-2 font-medium text-gray-700 bg-gray-200 rounded-lg transition-colors hover:bg-gray-300"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
