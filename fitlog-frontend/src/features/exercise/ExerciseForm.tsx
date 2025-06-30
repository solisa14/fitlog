import * as React from "react";
import {useState} from "react";
import type {Exercise, MuscleGroup, TrackingType} from "../../types";
import styles from "./Exercise.module.css";

interface ExerciseFormProps {
    exercise: Exercise | null;
    onCancel: () => void;
    onCreate: (exercise: Exercise) => void;
    onEdit: (exercise: Exercise) => void;
}

const allMuscleGroups: MuscleGroup[] = [
    "ARMS",
    "BACK",
    "CHEST",
    "CORE",
    "FULL_BODY",
    "GLUTES",
    "LEGS",
    "SHOULDERS",
];

const allTrackingTypes: TrackingType[] = [
    "REPS_AND_WEIGHT",
    "TIME_BASED",
    "REPS_ONLY",
    "DISTANCE_AND_DURATION",
];

export default function ExerciseForm({
                                         exercise,
                                         onCancel,
                                         onCreate,
                                         onEdit,
                                     }: ExerciseFormProps) {
    const [name, setName] = useState(exercise?.name || "");
    const [muscleGroups, setMuscleGroups] = useState<MuscleGroup[]>(
        exercise?.muscleGroups || []
    );
    const [trackingType, setTrackingType] = useState<TrackingType>(
        exercise?.trackingType || "REPS_AND_WEIGHT"
    );

    function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        if (exercise) {
            onEdit({id: exercise.id, name, muscleGroups, trackingType});
        } else {
            onCreate({id: "", name, muscleGroups, trackingType});
        }
    }

    function handleMuscleGroupChange(
        muscleGroup: MuscleGroup,
        isChecked: boolean
    ) {
        if (isChecked) {
            setMuscleGroups([...muscleGroups, muscleGroup]);
        } else {
            setMuscleGroups(muscleGroups.filter((mg) => mg !== muscleGroup));
        }
    }

    function handleTrackingTypeChange(e: React.ChangeEvent<HTMLSelectElement>) {
        setTrackingType(e.target.value as TrackingType);
    }

    return (
        <>
            <h2>{exercise ? "Edit Exercise" : "Create Exercise"}</h2>
            <form onSubmit={handleSubmit}>
                <div className={styles.formField}>
                    <label>Name</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="Enter exercise name"
                        required
                    />
                </div>

                <div className={styles.formField}>
                    <label>Muscle Groups</label>
                    <div className={styles.checkboxContainer}>
                        {allMuscleGroups.map((muscleGroup) => (
                            <div key={muscleGroup}
                                 className={styles.checkboxItem}>
                                <input
                                    type="checkbox"
                                    id={muscleGroup}
                                    checked={muscleGroups.includes(muscleGroup)}
                                    onChange={(e) =>
                                        handleMuscleGroupChange(muscleGroup, e.target.checked)
                                    }
                                />
                                <label htmlFor={muscleGroup}>
                                    {muscleGroup.toLowerCase().replace("_", " ")}
                                </label>
                            </div>
                        ))}
                    </div>
                </div>

                <div className={styles.formField}>
                    <label>Tracking Type</label>
                    <select
                        value={trackingType}
                        onChange={handleTrackingTypeChange}
                        required
                    >
                        {allTrackingTypes.map((type) => (
                            <option key={type} value={type}>
                                {type
                                    .toLowerCase()
                                    .replace(/_/g, " ")
                                    .replace(/\b\w/g, (l) => l.toUpperCase())}
                            </option>
                        ))}
                    </select>
                </div>

                <div className={styles.formActions}>
                    <button type="submit" className={styles.submitButton}>
                        {exercise ? "Update" : "Create"}
                    </button>
                    <button
                        type="button"
                        className={styles.cancelButton}
                        onClick={onCancel}
                    >
                        Cancel
                    </button>
                </div>
            </form>
        </>
    );
}
