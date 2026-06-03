package nl.vu.cs.softwaredesign.planning;
import nl.vu.cs.softwaredesign.domain.core.GoalType;

public class StrategyFactory {
    public static RecommendationStrategy createStrategy(GoalType goalType) {
        return switch (goalType) {
            case STRENGTH -> new StrengthStrategy();
            case WEIGHT_LOSS -> new WeightLossStrategy();
            case ENDURANCE -> new EnduranceStrategy();
        };
    }
}
