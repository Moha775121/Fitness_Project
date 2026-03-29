package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.tracking.RecoveryPlan;
import nl.vu.cs.softwaredesign.domain.tracking.WorkoutLog;

public class FatigueAnalyzer {

    public int calculateRecovery(int baseScore, WorkoutLog log) {
        int finalScore = baseScore + log.getFatigueLevel();

        if (log.getSleepHours() < 6.0) {
            finalScore += 3;
        }
        if (log.getStressLevel() > 7) {
            finalScore += 2;
        }
        return finalScore;
    }

    public RecoveryPlan createRecoveryPlan(int score) {
        if (score >= 8) {
            return new RecoveryPlan(true, "High fatigue detected. We recommend taking 2 extra rest days and focusing on sleep.");
        }
        return new RecoveryPlan(false, "System optimal. Ready to train.");
    }
}