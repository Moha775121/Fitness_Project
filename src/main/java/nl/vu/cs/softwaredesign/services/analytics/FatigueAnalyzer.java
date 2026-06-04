package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.tracking.RecoveryPlan;
import nl.vu.cs.softwaredesign.domain.tracking.WorkoutLog;

public class FatigueAnalyzer {

    public int calculateRecovery(int baseScore, WorkoutLog log) {
        final double MIN_HEALTHY_SLEEP = 6.0;
        final int SLEEP_PENALTY = 3;
        final int HIGH_STRESS_THRESHOLD = 7;
        final int STRESS_PENALTY = 2;

        int finalScore = baseScore + log.getFatigueLevel();

        if (log.getSleepHours() < MIN_HEALTHY_SLEEP) {
            finalScore += SLEEP_PENALTY;
        }
        if (log.getStressLevel() > HIGH_STRESS_THRESHOLD) {
            finalScore += STRESS_PENALTY;
        }

        return finalScore;
    }

    public RecoveryPlan createRecoveryPlan(int score) {
        final int HIGH_FATIGUE_THRESHOLD = 8;

        if (score >= HIGH_FATIGUE_THRESHOLD) {
            return new RecoveryPlan(true, "High fatigue detected. We recommend taking 2 extra rest days and focusing on sleep.");
        }
        return new RecoveryPlan(false, "System optimal. Ready to train.");    }
}