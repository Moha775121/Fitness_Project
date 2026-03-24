package nl.vu.cs.softwaredesign.domain.core;
// A class representing a user's fitness goal, which includes the target number of weeks to achieve the goal.
//Created the final Goal class with the specified attributes and a constructor to initialize them, along with getter methods to access the attributes.
public class Goal {
    private int targetWeeks;
    private double targetWeight;
    private GoalType goalType;

    public GoalType getGoalType() {
        return goalType;
    }

    public int getTargetWeeks() {
        return targetWeeks;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public Goal(int targetWeeks, double targetWeight, GoalType goalType) {
        this.targetWeeks = targetWeeks;
        this.targetWeight = targetWeight;
        this.goalType = goalType;
    }
}