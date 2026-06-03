package nl.vu.cs.softwaredesign.planning;

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
        List<Exercise> availableExercises = filterService.filter(allExercises, user.getConstraint().getAvailableEquipment());
        RecommendationStrategy strategy = StrategyFactory.createStrategy(user.getGoal().getGoalType());

        return strategy.generatePlan(user, user.getGoal(), user.getConstraint(), availableExercises);
    }
}
