package nl.vu.cs.softwaredesign.domain.tracking;

public class WorkoutLog {
    private final int fatigueLevel; // 1-10 scale
    private final int sleepHours;
    private final int stressLevel;  // 1-10 scale

    public WorkoutLog(int fatigueLevel, int sleepHours, int stressLevel) {
        this.fatigueLevel = fatigueLevel;
        this.sleepHours = sleepHours;
        this.stressLevel = stressLevel;
    }

    public int getFatigueLevel() { return fatigueLevel; }
    public int getSleepHours() { return sleepHours; }
    public int getStressLevel() { return stressLevel; }
}