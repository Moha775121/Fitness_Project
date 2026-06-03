package nl.vu.cs.softwaredesign.domain.core;

import nl.vu.cs.softwaredesign.domain.exercise.Equipment;
import java.util.List;

public class Constraint {
    private int daysAvailablePerWeek;
    private int timePerSessionMin;
    private List<Equipment> availableEquipment;

    public Constraint(int daysAvailablePerWeek, int timePerSessionMin, List<Equipment> availableEquipment) {
        this.daysAvailablePerWeek = daysAvailablePerWeek;
        this.timePerSessionMin = timePerSessionMin;
        this.availableEquipment = availableEquipment;
    }

    public void reduceTrainingDays() {
        if (this.daysAvailablePerWeek > 1) this.daysAvailablePerWeek--;
    }

    public int getDaysAvailablePerWeek() {
        return daysAvailablePerWeek;
    }

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }
}