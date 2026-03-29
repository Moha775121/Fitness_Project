package nl.vu.cs.softwaredesign.domain.plan;
import java.util.List;

public class DayOfTraining {
    private final DayOfWeek day;
    private final List<PlannedExercise> exercises;

    public DayOfTraining(DayOfWeek day, List<PlannedExercise> exercises) {
        this.day = day;
        this.exercises = exercises;
    }
    public DayOfWeek getDay() { return day; }
    public List<PlannedExercise> getExercises() { return exercises; }
}