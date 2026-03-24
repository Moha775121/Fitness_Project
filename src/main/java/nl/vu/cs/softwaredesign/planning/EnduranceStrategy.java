package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import java.util.List;

public class EnduranceStrategy implements RecommendationStrategy {
    @Override
    public TrainingPlan generatePlan(UserProfile user, List<Exercise> availableExercises) {
        System.out.println("Executing Endurance specific algorithm...");
        return new TrainingPlan();
    }
}
