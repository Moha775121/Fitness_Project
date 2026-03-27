package nl.vu.cs.softwaredesign.domain.tracking;

import java.time.LocalDate;

public class Milestone {
    private final MilestoneType type;
    private final LocalDate achievedOn;
    private final String message;

    public Milestone(MilestoneType type, LocalDate achievedOn, String message) {
        this.type = type;
        this.achievedOn = achievedOn;
        this.message = message;
    }

    public MilestoneType getType() { return type; }
    public String getMessage() { return message; }
    public LocalDate getAchievedOn() { return achievedOn; }
}
