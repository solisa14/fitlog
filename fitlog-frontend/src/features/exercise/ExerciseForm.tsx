import * as React from "react";
import {useEffect, useState} from "react";
import type {MuscleGroup, TrackingType} from "../../types/enum.ts";
import styles from "./Exercise.module.css";
import type {Exercise} from "../../types/exercise.ts";
import {
    fetchMuscleGroups,
    fetchTrackingTypes
} from "../../services/enum-service.ts";

interface ExerciseFormProps {
    exercise: Exercise | null;
    onCancel: () => void;
    onCreate: (exercise: Exercise) => void;
    onEdit: (exercise: Exercise) => void;
}

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
        exercise?.trackingType || {
            name: "None",
            displayName: "None"
        }
    );

    const [allMuscleGroups, setAllMuscleGroups] = useState<MuscleGroup[]>([]);
    const [allTrackingTypes, setAllTrackingTypes] = useState<TrackingType[]>([]);

    useEffect(() => {
        const loadData = async (): Promise<void> => {
            const muscleGroupsPromise: MuscleGroup[] = await fetchMuscleGroups();
            const trackingTypesPromise: TrackingType[] = await fetchTrackingTypes();
            setAllMuscleGroups(muscleGroupsPromise);
            setAllTrackingTypes(trackingTypesPromise);
        };
        loadData();
    }, [])

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
        setTrackingType(allTrackingTypes.find((type: TrackingType) => type.name === e.target.value) || {
            name: "None",
            displayName: "None"
        } as TrackingType);
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
                            <div key={muscleGroup.name}
                                 className={styles.checkboxItem}>
                                <input
                                    type="checkbox"
                                    id={muscleGroup.name}
                                    checked={muscleGroups.includes(muscleGroup)}
                                    onChange={(e) =>
                                        handleMuscleGroupChange(muscleGroup, e.target.checked)
                                    }
                                />
                                <label htmlFor={muscleGroup.name}>
                                    {muscleGroup.displayName}
                                </label>
                            </div>
                        ))}
                    </div>
                </div>

                <div className={styles.formField}>
                    <label>Tracking Type</label>
                    <select
                        value={trackingType.name}
                        onChange={handleTrackingTypeChange}
                        required
                    >
                        {allTrackingTypes.map((type) => (
                            <option key={type.name} value={type.name}>
                                {type.displayName}
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
