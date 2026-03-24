package nl.vu.cs.softwaredesign.services.filter;

import nl.vu.cs.softwaredesign.domain.exercise.Equipment;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class EquipmentFilterService {
    // Takes all exercises, and the user's available equipment, returns only what they can do
    public List<Exercise> filter(List<Exercise> allExercises, List<Equipment> availableEquipment) {
        Set<Equipment> availableSet = new HashSet<>(availableEquipment);

        return allExercises.stream()
        .filter(exercise -> availableSet.containsAll(exercise.getEquipmentNeeded()))
                .toList();
    }
}