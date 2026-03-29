package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.Constraint;
import nl.vu.cs.softwaredesign.domain.core.Goal;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.*;

import java.util.ArrayList;
import java.util.List;

public class StrengthStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, Goal goal, Constraint constraint, List<Exercise> exercises) {
        TrainingPlan plan = new TrainingPlan();
        DayOfWeek[] allDays = DayOfWeek.values();

        for (int w = 1; w <= goal.getTargetWeeks(); w++) {
            WeekPlan weekPlan = new WeekPlan(w);

            int daysToTrain = constraint.getDaysAvailablePerWeek();
            for (int d = 0; d < daysToTrain && d < allDays.length; d++) {
                List<PlannedExercise> dailyWorkout = new ArrayList<>();

                for (int i = 0; i < Math.min(4, exercises.size()); i++) {
                    dailyWorkout.add(new PlannedExercise(exercises.get(i), 5, 5, 120));
                }

                weekPlan.addDay(new DayOfTraining(allDays[d], dailyWorkout));
            }
            plan.addWeek(weekPlan);
        }

        return plan;
    }
}