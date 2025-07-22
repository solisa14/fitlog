# Planner File

**Current Plan:** Refactor exercise and turn it into reusable components for
other resources.

### Notes

- Top-down approach, refactor exercise page into reusable component.
- Create new props for the `ResourcePage.tsx`
- Figure out how to inject custom hooks into the component as props
- Possibly create a generic `useResource.ts` hook to handle common resource
  logic
- Custom hook might work since functionality of `useExercise.ts` is tied to the
  logic of the other components.
- Perhaps create a `useExercise.ts` and `useWorkout.ts` still to handle resource
  specific logic
- Specific hooks are probably useful for actually creating forms better suited
  for specific componentes while keeping everything else generic.

### Steps

1. Create generic types and interfaces

- Define `ResourceItem` base interface
- Create `ResourceHook` interface for hook contracts
- Define `ResourcePageProps` interface

2. Extract generic resource hook (useResource.ts)

- Create base hook with common CRUD operations
- Accept service functions as parameters
- Maintain state management patterns from `useExercises`

3. Create resource-specific hooks

- Keep `useExercises.ts` as wrapper around `useResource`
- Create `useWorkouts.ts` following same pattern
- Inject resource-specific service functions

4. Build generic components

- `ResourcePage.tsx` - Main container component
- `ResourceTable.tsx` - Generic table with configurable columns
- `ResourceRow.tsx` - Generic row component
- `ResourceForm.tsx` - Generic form with field configuration

5. Define component configuration

- Column definitions for tables
- Form field configurations
- Action button configurations
- Validation rules

6. Refactor existing exercise components

- Replace `ExercisePage.tsx` with `ResourcePage` usage
- Configure column definitions for exercises
- Create exercise-specific form configuration