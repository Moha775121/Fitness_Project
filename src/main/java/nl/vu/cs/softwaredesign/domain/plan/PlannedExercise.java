package nl.vu.cs.softwaredesign.domain.plan;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;

public class PlannedExercise {
    private final Exercise exercise;
    private final int sets;
    private final int reps;
    private final int restInSeconds;

    public PlannedExercise(Exercise exercise, int sets, int reps, int restInSeconds) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.restInSeconds = restInSeconds;
    }

    public Exercise getExercise() { return exercise; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public int getRestInSeconds() { return restInSeconds; }

    public double estimatedVolume() {
        return sets * reps * 1.0; // Simplified volume calculation
    }
}