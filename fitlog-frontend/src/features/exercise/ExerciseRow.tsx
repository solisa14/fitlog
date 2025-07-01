import type {Exercise} from "../../types/exercise.ts";

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
                <div>
                    {muscleGroups ? muscleGroups.map((muscleGroup, index) =>
                        <div key={index}>
                            {muscleGroup.displayName}
                        </div>) : "None"
                    }
                </div>
            </td>
            <td>
                {trackingType.displayName}
            </td>
            <td>
                <div>
                    <button onClick={handleEdit}>
                        Edit
                    </button>
                    <button onClick={handleDelete}>
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    );
}
