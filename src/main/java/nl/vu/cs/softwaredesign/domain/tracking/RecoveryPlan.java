package nl.vu.cs.softwaredesign.domain.tracking;

public class RecoveryPlan {
    private final boolean needsRecovery;
    private final String recommendation;

    public RecoveryPlan(boolean needsRecovery, String recommendation) {
        this.needsRecovery = needsRecovery;
        this.recommendation = recommendation;
    }

    public boolean getNeedsRecovery() { return needsRecovery; }
    public String getRecommendation() { return recommendation; }
}
