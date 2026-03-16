package nl.vu.cs.softwaredesign.domain.exercise;

import java.util.List;

public class Exercise {
    private String name;
    private Difficulty difficulty;
    private List<MuscleGroup> muscleGroups;
    private List<Equipment> equipmentNeeded;

    public Exercise(String name, Difficulty difficulty, List<MuscleGroup> muscleGroups, List<Equipment> equipmentNeeded) {
        this.name = name;
        this.difficulty = difficulty;
        this.muscleGroups = muscleGroups;
        this.equipmentNeeded = equipmentNeeded;
    }
}
