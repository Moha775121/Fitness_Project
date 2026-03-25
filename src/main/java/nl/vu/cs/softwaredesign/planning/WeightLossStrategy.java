package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.*;

import java.util.ArrayList;
import java.util.List;

public class WeightLossStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, List<Exercise> availableExercises) {
        TrainingPlan plan = new TrainingPlan();

        List<PlannedExercise> workoutA = new ArrayList<>();
        List<PlannedExercise> workoutB = new ArrayList<>();

        // Distribute exercises alternately to create two distinct workouts
        for (int i = 0; i < availableExercises.size(); i++) {
            Exercise ex = availableExercises.get(i);
            if (i % 2 == 0) {
                // Workout A: Higher reps, lower sets for cardio effect
                workoutA.add(new PlannedExercise(ex, 3, 15));
            } else {
                // Workout B: Slightly heavier, more sets
                workoutB.add(new PlannedExercise(ex, 4, 12));
            }
        }

        // Only schedule the days if exercises actually exist for them
        if (!workoutA.isEmpty()) {
            plan.addDay(new DayOfTraining(DayOfWeek.MONDAY, workoutA));
        }
        if (!workoutB.isEmpty()) {
            plan.addDay(new DayOfTraining(DayOfWeek.WEDNESDAY, workoutB));
        }

        return plan;
    }
}