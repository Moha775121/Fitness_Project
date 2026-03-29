package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.Constraint;
import nl.vu.cs.softwaredesign.domain.core.Goal;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.*;

import java.util.ArrayList;
import java.util.List;

public class WeightLossStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, Goal goal, Constraint constraint, List<Exercise> exercises) {
        TrainingPlan plan = new TrainingPlan();
        DayOfWeek[] allDays = DayOfWeek.values();

        for (int w = 1; w <= goal.getTargetWeeks(); w++) {
            WeekPlan weekPlan = new WeekPlan(w);

            int daysToTrain = constraint.getDaysAvailablePerWeek();
            for (int d = 0; d < daysToTrain && d < allDays.length; d++) {
                List<PlannedExercise> dailyWorkout = new ArrayList<>();

                // Add exercises (3 sets, 15 reps, 60 seconds rest)
                for (int i = 0; i < exercises.size(); i++) {
                    dailyWorkout.add(new PlannedExercise(exercises.get(i), 3, 15, 60));
                }

                weekPlan.addDay(new DayOfTraining(allDays[d], dailyWorkout));
            }
            plan.addWeek(weekPlan); // Add the week to the plan
        }

        return plan;
    }
}