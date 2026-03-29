package nl.vu.cs.softwaredesign.domain.tracking;

import java.time.LocalDate;

public class WorkoutLog {
    private final LocalDate date;
    private final int fatigueLevel;
    private final double sleepHours;
    private final int stressLevel;

    public WorkoutLog(LocalDate date, int fatigueLevel, double sleepHours, int stressLevel) {
        this.date = date;
        this.fatigueLevel = fatigueLevel;
        this.sleepHours = sleepHours;
        this.stressLevel = stressLevel;
    }

    public LocalDate getDate() { return date; }
    public int getFatigueLevel() { return fatigueLevel; }
    public double getSleepHours() { return sleepHours; }
    public int getStressLevel() { return stressLevel; }
}