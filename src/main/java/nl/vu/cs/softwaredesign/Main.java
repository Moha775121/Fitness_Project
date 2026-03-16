package nl.vu.cs.softwaredesign;
import java.util.logging.Logger;
import java.util.logging.Level;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.repository.ExerciseRepository;
import nl.vu.cs.softwaredesign.repository.JsonExerciseRepository;
import nl.vu.cs.softwaredesign.ui.UserProfileCLI;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserProfileCLI cli = new UserProfileCLI();
        UserProfile user = cli.promptForProfile(scanner);

        ExerciseRepository repo = new JsonExerciseRepository("src/main/resources/exercises.json");

        LOGGER.log(Level.INFO, "Loaded {0} exercises from the database.", repo.getAll().size());
        LOGGER.log(Level.INFO, user::toString);
        LOGGER.log(Level.INFO, "System is ready for the Planning Engine...");

        scanner.close();
    }
}