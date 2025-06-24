import type {Exercise} from "../../types";
import ExerciseRow from "./ExerciseRow.tsx";
import styles from "./Exercise.module.css";

interface ExerciseTableProps {
    exercises: Exercise[];
    onEdit: (exercise: Exercise) => void;
    onDelete: (id: string) => void;
}

export default function ExerciseTable({
                                          exercises,
                                          onEdit,
                                          onDelete,
                                      }: ExerciseTableProps) {
    if (exercises.length === 0) {
        return (
            <div className={styles.emptyState}>
                <p>No exercises found. Create your first exercise to get
                    started!</p>
            </div>
        );
    }

    return (
        <table className={styles.exerciseTable}>
            <thead>
            <tr>
                <th>Name</th>
                <th>Muscle Groups</th>
                <th>Tracking Type</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {exercises.map((exercise: Exercise, index: number) => (
                <ExerciseRow
                    key={index}
                    exercise={exercise}
                    onEdit={onEdit}
                    onDelete={onDelete}
                />
            ))}
            </tbody>
        </table>
    );
}
