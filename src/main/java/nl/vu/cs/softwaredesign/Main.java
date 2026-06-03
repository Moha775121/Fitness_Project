package nl.vu.cs.softwaredesign;
import java.util.logging.Logger;
import java.util.logging.Level;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.domain.tracking.RecoveryPlan;
import nl.vu.cs.softwaredesign.domain.tracking.WeightForecastResult;
import nl.vu.cs.softwaredesign.domain.tracking.WorkoutLog;
import nl.vu.cs.softwaredesign.planning.PlanGenerator;
import nl.vu.cs.softwaredesign.repository.ExerciseRepository;
import nl.vu.cs.softwaredesign.repository.JsonExerciseRepository;
import nl.vu.cs.softwaredesign.services.analytics.FatigueAnalyzer;
import nl.vu.cs.softwaredesign.services.analytics.WeightPredictor;
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

        LOGGER.log(Level.INFO, "Generating plan...");
        TrainingPlan plan = generator.generate(user, repo.getAll());
        LOGGER.log(Level.INFO, "Plan generated successfully!");

        LOGGER.log(Level.INFO, "Loaded {0} exercises from the database.", repo.getAll().size());
        LOGGER.log(Level.INFO, user::toString);
        LOGGER.log(Level.INFO, "System is ready for the Planning Engine...");

        LOGGER.log(Level.INFO, "Exporting your plan to PDF...");

        WeightPredictor predictor = new WeightPredictor();
        WeightForecastResult forecast = predictor.predict(user);

        RecoveryPlan recovery = new RecoveryPlan(false, "System optimal. Ready to train.");

        LOGGER.log(Level.INFO, "Exporting your plan to PDF...");
        PdfExporter exporter = new PdfExporter();

        boolean success = exporter.export(plan, user, forecast, recovery);

        if (success) {
            LOGGER.log(Level.INFO, "Success! Check your project folder for 'MyTrainingPlan.pdf'.");
        } else {
            LOGGER.log(Level.WARNING, "Failed to export PDF.");
        }

        LOGGER.log(Level.INFO, "=== Application Finished ===");

        LOGGER.log(Level.INFO, "Simulating Week 1 completion and high fatigue...");
        WorkoutLog log = new WorkoutLog(java.time.LocalDate.now(), 8, 5.0, 8);
        FatigueAnalyzer analyzer = new FatigueAnalyzer();
        RecoveryPlan newRecovery = analyzer.createRecoveryPlan(analyzer.calculateRecovery(0, log));

        if (newRecovery.isNeedsRecovery()) {
            user.getConstraint().reduceTrainingDays();
            TrainingPlan adaptedPlan = generator.generate(user, repo.getAll());
            exporter.export(adaptedPlan, user, forecast, newRecovery);
            LOGGER.log(Level.INFO, "Adapted plan generated and re-exported due to high fatigue.");
        }

        scanner.close();
    }
}