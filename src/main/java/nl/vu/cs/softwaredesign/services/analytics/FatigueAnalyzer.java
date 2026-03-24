package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.tracking.WorkoutLog;

public class FatigueAnalyzer {

    // Computes a fatigue score based on the user's log
    public int calculateFatigueScore(WorkoutLog log) {
        int score = log.getFatigueLevel();

        // Add penalties for bad sleep or high life stress
        if (log.getSleepHours() < 6) {
            score += 3;
        }
        if (log.getStressLevel() > 7) {
            score += 2;
        }

        return score;
    }

    // Determines if the system needs to step in and adjust workloads
    public boolean requiresRecoveryPlan(int fatigueScore) {
        // If the score hits 8 or higher, trigger recovery mode
        return fatigueScore >= 8;
    }
}