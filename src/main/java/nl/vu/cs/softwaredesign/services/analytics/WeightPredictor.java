package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.core.GoalType;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.tracking.WeightForecastResult;
import nl.vu.cs.softwaredesign.domain.core.Sex;

import java.util.ArrayList;
import java.util.List;

public class WeightPredictor {

    // Runs a simple Calories-In vs Calories-Out model over N weeks
    public WeightForecastResult predict(UserProfile user) {
        int weeks = user.getGoal().getTargetWeeks();
        double currentWeight = user.getWeightKg();
        List<Double> weeklyWeights = new ArrayList<>();

        for (int i = 1; i <= weeks; i++) {
            // 1. Calculate Base TDEE
            double bmr = currentWeight * 24.0;
            if (user.getSex() == Sex.FEMALE) {
                bmr *= 0.9;
            } else if (user.getSex() == Sex.MALE) {
                bmr *= 1.1;
            }

            double stepCalories = user.getAvgStepsPerDay() * 0.04;
            double workoutCalories = (user.getTrainingFrequencyPerWeek() * 400.0) / 7.0;
            double tdee = bmr + stepCalories + workoutCalories;

            // Override user's CLI input with a mathematically correct diet plan based on their goal!
            double dailyDifference;
            if (user.getGoal().getGoalType() == GoalType.WEIGHT_LOSS) {
                dailyDifference = -500.0; // Force a healthy 500 calorie deficit for weight loss
            } else if (user.getGoal().getGoalType() == GoalType.STRENGTH) {
                dailyDifference = 300.0; // Force a slight surplus to build muscle
            } else {
                dailyDifference = user.getAvgCaloriesPerDay() - tdee; // Use what they entered
            }

            double weeklyDifference = dailyDifference * 7.0;

            // 3. Convert calories to kg
            double weightChange = weeklyDifference / 7700.0;
            currentWeight += weightChange;

            currentWeight = Math.round(currentWeight * 100.0) / 100.0;
            weeklyWeights.add(currentWeight);
        }

        return new WeightForecastResult(currentWeight, weeklyWeights);
    }
}