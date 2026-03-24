package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.GoalType;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.services.filter.EquipmentFilterService;

import java.util.List;

public class PlanGenerator {
    private final EquipmentFilterService filterService;

    // We inject the filter service here
    public PlanGenerator(EquipmentFilterService filterService) {
        this.filterService = filterService;
    }

    // This is the main method called from Activity Diagram 2
    public TrainingPlan generate(UserProfile user, List<Exercise> allExercises) {
        // 1. Filter exercises based on user's equipment (Kelvin's feature)
        List<Exercise> availableExercises = filterService.filter(allExercises, user.getConstraint().getAvailableEquipment());

        // 2. Select the right strategy based on the user's goal (Anna's feature)
        RecommendationStrategy strategy = selectStrategy(user.getGoal().getGoalType());

        // 3. Delegate the actual math/generation to the chosen strategy
        return strategy.generatePlan(user, availableExercises);
    }

    // A modern Java switch statement to act as our "Factory"
    private RecommendationStrategy selectStrategy(GoalType goalType) {
        return switch (goalType) {
            case STRENGTH -> new StrengthStrategy();
            case WEIGHT_LOSS -> new WeightLossStrategy();
            case ENDURANCE -> new EnduranceStrategy();
        };
    }
}