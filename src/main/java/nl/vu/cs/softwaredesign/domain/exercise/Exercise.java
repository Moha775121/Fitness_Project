package nl.vu.cs.softwaredesign.domain.exercise;

import java.util.List;

public class Exercise {
    private final String name;
    private final Difficulty difficulty;
    private final List<MuscleGroup> muscleGroups;
    private final List<Equipment> equipmentNeeded;
    private final ExerciseType type;

    public Exercise(String name, Difficulty difficulty, List<MuscleGroup> muscleGroups, List<Equipment> equipmentNeeded, ExerciseType type) {
        this.name = name;
        this.difficulty = difficulty;
        this.muscleGroups = muscleGroups;
        this.equipmentNeeded = equipmentNeeded;
        this.type = type;
    }

    public List<Equipment> getEquipmentNeeded() {
        return this.equipmentNeeded;
    }

    public String getName() {
        return this.name;
    }
}