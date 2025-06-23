import type {Exercise} from "./ExercisePage.tsx";
import styles from "./Exercise.module.css";

interface ExerciseRowProps {
    exercise: Exercise;
    onEdit: (exercise: Exercise) => void;
    onDelete: (id: string) => void;
}

export default function ExerciseRow({
                                        exercise: {
                                            id,
                                            name,
                                            muscleGroups,
                                            trackingType
                                        },
                                        onEdit,
                                        onDelete,
                                    }: ExerciseRowProps) {
    function handleEdit() {
        onEdit({id, name, muscleGroups, trackingType});
    }

    function handleDelete() {
        onDelete(id);
    }

    return (
        <tr id={id}>
            <td>{name}</td>
            <td>
                <div className={styles.muscleGroupContainer}>
                    {muscleGroups ? muscleGroups.map((muscleGroup, index) =>
                        <div key={index} className={styles.muscleGroupTag}>
                            {muscleGroup.toLowerCase()
                                .split('_')
                                .map(word =>
                                    word.charAt(0).toUpperCase() + word.slice(1))
                                .join(' ')}
                        </div>) : "None"
                    }
                </div>
            </td>
            <td>
                {trackingType.toLowerCase().split('_').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ')}
            </td>
            <td>
                <div className={styles.actionButtons}>
                    <button className={styles.editButton} onClick={handleEdit}>
                        Edit
                    </button>
                    <button className={styles.deleteButton}
                            onClick={handleDelete}>
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    );
}
