package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.services.filter.EquipmentFilterService;
import nl.vu.cs.softwaredesign.repository.ExerciseRepository;
import nl.vu.cs.softwaredesign.repository.JsonExerciseRepository;

import java.util.List;

public class PlanGenerator {
    private final EquipmentFilterService filterService;

    public PlanGenerator(EquipmentFilterService filterService) {
        this.filterService = filterService;
    }

    public TrainingPlan generate(UserProfile user) {
        ExerciseRepository repo = new JsonExerciseRepository("src/main/resources/exercises.json");
        List<Exercise> allExercises = repo.getAll();

        List<Exercise> availableExercises = filterService.filter(allExercises, user.getConstraint().getAvailableEquipment());
        RecommendationStrategy strategy = StrategyFactory.createStrategy(user.getGoal().getGoalType());

        return strategy.generatePlan(user, user.getGoal(), user.getConstraint(), availableExercises);
    }
}