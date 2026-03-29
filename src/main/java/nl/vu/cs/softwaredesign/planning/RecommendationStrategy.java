package nl.vu.cs.softwaredesign.planning;

import nl.vu.cs.softwaredesign.domain.core.Constraint;
import nl.vu.cs.softwaredesign.domain.core.Goal;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;

import java.util.List;

public interface RecommendationStrategy {
    TrainingPlan generatePlan(UserProfile user, Goal goal, Constraint constraint, List<Exercise> exercises);
}