
// package nl.vu.cs.softwaredesign.services.export;

// import com.itextpdf.text.Document;
// import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.Font;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;
// import nl.vu.cs.softwaredesign.domain.core.UserProfile;
// import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
// import nl.vu.cs.softwaredesign.domain.tracking.WeightForecastResult;
// import nl.vu.cs.softwaredesign.services.analytics.MilestoneTracker; 
// import nl.vu.cs.softwaredesign.services.analytics.WeightPredictor;

// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// public class PdfExporter {
//     private static final Logger LOGGER = Logger.getLogger(PdfExporter.class.getName());

//     public boolean export(TrainingPlan plan, UserProfile user, String filePath) {
//         Document document = new Document();

//         try {
//             PdfWriter.getInstance(document, new FileOutputStream(filePath));
//             document.open();

//             // Add Title
//             Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
//             document.add(new Paragraph("Fitness Training Plan", titleFont));
//             document.add(new Paragraph("\n")); // Blank line

//             // Add User Details
//             document.add(new Paragraph("--- User Profile ---"));
//             document.add(new Paragraph("Current Weight: " + user.getWeightKg() + " kg"));
//             document.add(new Paragraph("Primary Goal: " + user.getGoal().getGoalType()));
//             document.add(new Paragraph("\n"));

//             // Add Plan Details
//             document.add(new Paragraph("--- Weekly Schedule ---"));
//             document.add(new Paragraph("This plan is designed to be completed over " + user.getConstraint().getDaysAvailablePerWeek() + " days per week."));
//             document.add(new Paragraph("Please remember to log your fatigue and stress levels after each session!"));

//             // Add Plan Details
//             document.add(new Paragraph("--- Weekly Schedule ---"));

//             if (plan.getDays().isEmpty()) {
//                 document.add(new Paragraph("No workouts scheduled."));
//             } else {
//                 for (nl.vu.cs.softwaredesign.domain.plan.DayOfTraining day : plan.getDays()) {
//                     document.add(new Paragraph("\n" + day.getDay() + " WORKOUT:"));
//                     for (nl.vu.cs.softwaredesign.domain.plan.PlannedExercise plannedEx : day.getExercises()) {
//                         document.add(new Paragraph(" - " + plannedEx.getExercise().getName() +
//                                 ": " + plannedEx.getSets() + " sets of " + plannedEx.getReps() + " reps"));
//                     }
//                 }
//             }

//             // The Masterpiece Polish: Add the Weight Prediction!
//             document.add(new Paragraph("\n--- AI Weight Forecast ---"));
//             WeightPredictor predictor = new WeightPredictor();
//             WeightForecastResult forecast = predictor.predict(user);
//             document.add(new Paragraph("If you stick to this plan and your diet, in " + user.getGoal().getTargetWeeks() +
//                     " weeks, your predicted weight is: " + forecast.getFinalPredictedWeight() + " kg!"));

//             document.add(new Paragraph("Starting Weight: " + user.getWeightKg() + " kg"));
//             document.add(new Paragraph("Predicted Weight in " + user.getGoal().getTargetWeeks() + " weeks: " + forecast.getFinalPredictedWeight() + " kg"));

//             // Integrate Milestone Tracker!
//             document.add(new Paragraph("\n--- Projected Milestones ---"));
//             MilestoneTracker tracker = new MilestoneTracker();

//             // Simulate that they completed their first 3 sessions based on this new plan
//             java.util.List<nl.vu.cs.softwaredesign.domain.tracking.Milestone> simulatedBadges = tracker.evaluate(user, 3);

//             if (simulatedBadges.isEmpty()) {
//                 document.add(new Paragraph("Keep training to unlock your first badge!"));
//             } else {
//                 for (nl.vu.cs.softwaredesign.domain.tracking.Milestone badge : simulatedBadges) {
//                     document.add(new Paragraph("🏆 Badge Unlocked: " + badge.getType()));
//                     document.add(new Paragraph("   \"" + badge.getMessage() + "\""));
//                 }
//             }

//             document.close();
//             return true;

//         } catch (DocumentException | IOException e) {
//             LOGGER.log(Level.SEVERE, "Failed to create PDF document", e);
//             return false;
//         }
//     }
// }