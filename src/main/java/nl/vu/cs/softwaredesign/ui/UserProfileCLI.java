package nl.vu.cs.softwaredesign.ui;

import nl.vu.cs.softwaredesign.domain.core.*;
import nl.vu.cs.softwaredesign.domain.exercise.Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserProfileCLI {

    // We pass the Scanner in so we can mock it later for SonarQube testing!
    // This is CLI Sytem.out and I know it.
    @SuppressWarnings("java:S106")
    public UserProfile promptForProfile(Scanner scanner) {
        System.out.println("=== Welcome to the Fitness Training Planner ===");

        System.out.print("Enter your weight in kg: ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your biological sex for BMR calculation (MALE or FEMALE): ");
        Sex sex = Sex.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter average calories consumed per day: ");
        int calories = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter average steps per day: ");
        int steps = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter days available to train per week (1-7): ");
        int days = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your goal (WEIGHT_LOSS, STRENGTH, or ENDURANCE): ");
        GoalType goalType = GoalType.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter target weeks for goal: ");
        int targetWeeks = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter target weight (kg): ");
        double targetWeight = Double.parseDouble(scanner.nextLine());
        Goal goal = new Goal(targetWeeks, targetWeight, goalType);

        Constraint constraint = new Constraint(days, 60, List.of(Equipment.BODYWEIGHT, Equipment.DUMBBELL));

        System.out.println("Profile created successfully!");

        return new UserProfile(weight, calories, steps, days, sex, goal, constraint);
    }
}