package nl.vu.cs.softwaredesign.domain.core;

public class UserProfile {
    private double weightKg;
    private int avgCaloriesPerDay;
    private int avgStepsPerDay;
    private int trainingFrequencyPerWeek;
    private Sex sex;
    private Goal goal;
    private Constraint constraint;

    public Sex getSex() {
        return sex;
    }

    public int getAvgCaloriesPerDay() {
        return avgCaloriesPerDay;
    }

    public int getAvgStepsPerDay() {
        return avgStepsPerDay;
    }

    public int getTrainingFrequencyPerWeek() {
        return trainingFrequencyPerWeek;
    }

    public Goal getGoal() {
        return goal;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public UserProfile(double weightKg, int avgCaloriesPerDay, int avgStepsPerDay, int trainingFrequencyPerWeek, Sex sex, Goal goal, Constraint constraint) {
        this.weightKg = weightKg;
        this.avgCaloriesPerDay = avgCaloriesPerDay;
        this.avgStepsPerDay = avgStepsPerDay;
        this.trainingFrequencyPerWeek = trainingFrequencyPerWeek;
        this.sex = sex;
        this.goal = goal;
        this.constraint = constraint;
    }
}
