package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.core.Constraint;
import nl.vu.cs.softwaredesign.domain.core.Goal;
import nl.vu.cs.softwaredesign.domain.core.GoalType;
import nl.vu.cs.softwaredesign.domain.core.Sex;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.tracking.Milestone;
import nl.vu.cs.softwaredesign.domain.tracking.MilestoneType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MilestoneTrackerTest {

    @Test
    void testEvaluate_AwardsTargetWeightBadge() {
        // Setup: User wants to reach 75kg, and they are currently at 74.5kg!
        Goal goal = new Goal(12, 75.0, GoalType.WEIGHT_LOSS);
        Constraint constraint = new Constraint(3, 60, new ArrayList<>());
        UserProfile user = new UserProfile(74.5, 2000, 5000, 3, Sex.MALE, goal, constraint);

        MilestoneTracker tracker = new MilestoneTracker();

        // Execute: Check milestones after 5 sessions
        List<Milestone> earned = tracker.evaluate(user, 5);

        // Assert: They should have exactly 1 badge (TARGET_WEIGHT)
        assertEquals(1, earned.size());
        assertEquals(MilestoneType.TARGET_WEIGHT, earned.get(0).getType());
    }
}
