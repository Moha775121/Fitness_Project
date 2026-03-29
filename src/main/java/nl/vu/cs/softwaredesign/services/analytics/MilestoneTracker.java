package nl.vu.cs.softwaredesign.services.analytics;

import nl.vu.cs.softwaredesign.domain.core.GoalType;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.tracking.Milestone;
import nl.vu.cs.softwaredesign.domain.tracking.MilestoneType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MilestoneTracker {

    // Evaluates the user's current status and returns any newly unlocked badges
    public List<Milestone> evaluate(UserProfile user, int totalSessionsLogged) {
        List<Milestone> newlyEarned = new ArrayList<>();

        // Badge 1: First Week Completed (Assuming 3 sessions = 1 week for this logic)
        if (totalSessionsLogged == 3) {
            newlyEarned.add(new Milestone(MilestoneType.FIRST_WEEK, LocalDate.now(), "Great job! You finished your first week of training."));
        }

        // Badge 2: Consistency/Session Count (Every 10 sessions)
        if (totalSessionsLogged > 0 && totalSessionsLogged % 10 == 0) {
            newlyEarned.add(new Milestone(MilestoneType.SESSION_COUNT, LocalDate.now(), "Dedication! You've logged " + totalSessionsLogged + " sessions."));
        }

        // Badge 3: Target Weight Achieved
        if (user.getGoal().getGoalType() == GoalType.WEIGHT_LOSS) {
            if (user.getWeightKg() <= user.getGoal().getTargetWeight()) {
                newlyEarned.add(new Milestone(MilestoneType.TARGET_WEIGHT, LocalDate.now(), "Congratulations! You hit your target weight."));
            }
        }

        return newlyEarned;
    }
}
