package nl.vu.cs.softwaredesign.domain.plan;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;

public class PlannedExercise {
    private final Exercise exercise;
    private final int sets;
    private final int reps;

    public PlannedExercise(Exercise exercise, int sets, int reps) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }
    public Exercise getExercise() { return exercise; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
}