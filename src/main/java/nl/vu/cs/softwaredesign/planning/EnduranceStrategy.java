package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.DayOfTraining;
import nl.vu.cs.softwaredesign.domain.plan.DayOfWeek;
import nl.vu.cs.softwaredesign.domain.plan.PlannedExercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

public class EnduranceStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, List<Exercise> availableExercises) {
        TrainingPlan plan = new TrainingPlan();
        List<PlannedExercise> enduranceWorkout = new ArrayList<>();

        // Endurance focus: 3 sets of 20 reps (Cardio/Muscular stamina)
        for (int i = 0; i < availableExercises.size(); i++) {
            Exercise ex = availableExercises.get(i);
            enduranceWorkout.add(new PlannedExercise(ex, 3, 20));
        }

        // Endurance can usually be trained more frequently
        if (!enduranceWorkout.isEmpty()) {
            plan.addDay(new DayOfTraining(DayOfWeek.MONDAY, enduranceWorkout));
            plan.addDay(new DayOfTraining(DayOfWeek.WEDNESDAY, enduranceWorkout));
            plan.addDay(new DayOfTraining(DayOfWeek.FRIDAY, enduranceWorkout));
        }

        return plan;
    }
}