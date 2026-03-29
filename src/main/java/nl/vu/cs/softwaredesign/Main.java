package nl.vu.cs.softwaredesign;
import java.util.logging.Logger;
import java.util.logging.Level;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.planning.PlanGenerator;
import nl.vu.cs.softwaredesign.repository.ExerciseRepository;
import nl.vu.cs.softwaredesign.repository.JsonExerciseRepository;
import nl.vu.cs.softwaredesign.services.filter.EquipmentFilterService;
import nl.vu.cs.softwaredesign.ui.UserProfileCLI;
import nl.vu.cs.softwaredesign.services.export.PdfExporter;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserProfileCLI cli = new UserProfileCLI();
        UserProfile user = cli.promptForProfile(scanner);

        ExerciseRepository repo = new JsonExerciseRepository("src/main/resources/exercises.json");

        EquipmentFilterService filterService = new EquipmentFilterService();
        PlanGenerator generator = new PlanGenerator(filterService);

        System.out.println("Generating plan...");
        TrainingPlan plan = generator.generate(user, repo.getAll());
        System.out.println("Plan generated successfully!");

        LOGGER.log(Level.INFO, "Loaded {0} exercises from the database.", repo.getAll().size());
        LOGGER.log(Level.INFO, user::toString);
        LOGGER.log(Level.INFO, "System is ready for the Planning Engine...");

        System.out.println("Exporting your plan to PDF...");

        PdfExporter exporter = new PdfExporter();
        boolean success = exporter.export(plan, user, "MyTrainingPlan.pdf");

        if (success) {
            System.out.println("Success! Check your project folder for 'MyTrainingPlan.pdf'.");
        } else {
            System.out.println("Failed to export PDF.");
        }

        System.out.println("=== Application Finished ===");

        scanner.close();
    }
}