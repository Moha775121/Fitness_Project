package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.DayOfTraining;
import nl.vu.cs.softwaredesign.domain.plan.DayOfWeek;
import nl.vu.cs.softwaredesign.domain.plan.PlannedExercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

public class StrengthStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, List<Exercise> availableExercises) {
        TrainingPlan plan = new TrainingPlan();
        List<PlannedExercise> strengthWorkout = new ArrayList<>();

        // Strength focus: 5 sets of 5 reps (Heavy load)
        for (int i = 0; i < Math.min(4, availableExercises.size()); i++) {
            Exercise ex = availableExercises.get(i);
            strengthWorkout.add(new PlannedExercise(ex, 5, 5));
        }

        // Strength usually requires rest days between identical heavy sessions
        if (!strengthWorkout.isEmpty()) {
            plan.addDay(new DayOfTraining(DayOfWeek.MONDAY, strengthWorkout));
            plan.addDay(new DayOfTraining(DayOfWeek.THURSDAY, strengthWorkout));
        }

        return plan;
    }
}