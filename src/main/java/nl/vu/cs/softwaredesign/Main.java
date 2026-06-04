package nl.vu.cs.softwaredesign;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.domain.tracking.Milestone;
import nl.vu.cs.softwaredesign.domain.tracking.RecoveryPlan;
import nl.vu.cs.softwaredesign.domain.tracking.WeightForecastResult;
import nl.vu.cs.softwaredesign.domain.tracking.WorkoutLog;
import nl.vu.cs.softwaredesign.planning.PlanGenerator;
import nl.vu.cs.softwaredesign.repository.ExerciseRepository;
import nl.vu.cs.softwaredesign.repository.JsonExerciseRepository;
import nl.vu.cs.softwaredesign.services.analytics.FatigueAnalyzer;
import nl.vu.cs.softwaredesign.services.analytics.MilestoneTracker;
import nl.vu.cs.softwaredesign.services.analytics.WeightPredictor;
import nl.vu.cs.softwaredesign.services.filter.EquipmentFilterService;
import nl.vu.cs.softwaredesign.ui.UserProfileCLI;
import nl.vu.cs.softwaredesign.services.export.PdfExporter;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String EXERCISE_DB_PATH = "src/main/resources/exercises.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserProfileCLI cli = new UserProfileCLI();
        UserProfile user = cli.promptForProfile(scanner);

        ExerciseRepository repo = new JsonExerciseRepository(EXERCISE_DB_PATH);

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
            LOGGER.log(Level.INFO, "Success! Check your project folder for your newly generated Training Plan PDF.");
        } else {
            LOGGER.log(Level.WARNING, "Failed to export PDF.");
        }

        LOGGER.log(Level.INFO, "=== Initial Planning Finished ===");

        System.out.println("\n--- END OF WEEK 1 ---");
        System.out.println("Let's log your week to adapt your next plan.");
        System.out.print("Enter your fatigue level (1-10): ");
        int userFatigue = scanner.nextInt();
        System.out.print("Enter average sleep hours: ");
        double userSleep = scanner.nextDouble();
        System.out.print("Enter stress level (1-10): ");
        int userStress = scanner.nextInt();
        System.out.print("Enter total sessions completed this week: ");
        int completedSessions = scanner.nextInt();

        WorkoutLog log = new WorkoutLog(java.time.LocalDate.now(), userFatigue, userSleep, userStress);
        FatigueAnalyzer analyzer = new FatigueAnalyzer();
        RecoveryPlan newRecovery = analyzer.createRecoveryPlan(analyzer.calculateRecovery(0, log));

        if (newRecovery.getNeedsRecovery()) {
            LOGGER.log(Level.WARNING, "System adapting to user state: " + newRecovery.getRecommendation());
            user.getConstraint().reduceTrainingDays();
            TrainingPlan adaptedPlan = generator.generate(user, repo.getAll());
            exporter.export(adaptedPlan, user, forecast, newRecovery);
            LOGGER.log(Level.INFO, "Adapted plan generated and re-exported due to high fatigue.");
        } else {
            LOGGER.log(Level.INFO, "Great job! " + newRecovery.getRecommendation());
        }

        LOGGER.log(Level.INFO, "Evaluating user milestones...");
        MilestoneTracker milestoneTracker = new MilestoneTracker();

        List<Milestone> earnedMilestones = milestoneTracker.evaluate(user, completedSessions);

        if (earnedMilestones != null && !earnedMilestones.isEmpty()) {
            System.out.println("\n🏆 CONGRATULATIONS! You unlocked new milestones:");
            for (Milestone m : earnedMilestones) {
                System.out.println(" - " + m.getType() + " : " + m.getMessage());
            }
        } else {
            LOGGER.log(Level.INFO, "Keep training to unlock your first milestone!");
        }

        scanner.close();
    }
}