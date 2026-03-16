package nl.vu.cs.softwaredesign.domain.core;

public class Goal {
    private int targetWeeks;
    private double targetWeight;
    private GoalType goalType;

    public Goal(int targetWeeks, double targetWeight, GoalType goalType) {
        this.targetWeeks = targetWeeks;
        this.targetWeight = targetWeight;
        this.goalType = goalType;
    }
}