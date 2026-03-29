package nl.vu.cs.softwaredesign.domain.plan;

import java.util.ArrayList;
import java.util.List;

public class WeekPlan {
    private final int weekNumber;
    private final List<DayOfTraining> daysOfTraining;

    public WeekPlan(int weekNumber) {
        this.weekNumber = weekNumber;
        this.daysOfTraining = new ArrayList<>();
    }

    public int getWeekNumber() { return weekNumber; }
    public List<DayOfTraining> getDaysOfTraining() { return daysOfTraining; }

    public void addDay(DayOfTraining day) {
        this.daysOfTraining.add(day);
    }
}
