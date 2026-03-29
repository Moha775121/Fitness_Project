package nl.vu.cs.softwaredesign.domain.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingPlan {
    private final LocalDate startDate;
    private final int totalWeeks;
    private final List<WeekPlan> weekPlans;

    public TrainingPlan() {
        this.startDate = LocalDate.now();
        this.totalWeeks = 4;
        this.weekPlans = new ArrayList<>();
    }

    public LocalDate getStartDate() { return startDate; }
    public int getTotalWeeks() { return totalWeeks; }
    public List<WeekPlan> getWeekPlans() { return weekPlans; }

    public void addWeek(WeekPlan weekPlan) {
        this.weekPlans.add(weekPlan);
    }
}