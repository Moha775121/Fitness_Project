package nl.vu.cs.softwaredesign.domain.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingPlan {
    private final LocalDate startDate;
    private final int totalWeeks;
    private final List<DayOfTraining> days;

    public List<DayOfTraining> getDays() {
        return days;
    }

    public void addDay(DayOfTraining day) {
        this.days.add(day);
    }

    public TrainingPlan() {
        this.startDate = LocalDate.now();
        this.totalWeeks = 4;
        this.days = new ArrayList<>();
    }
}