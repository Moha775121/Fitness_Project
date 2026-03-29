package nl.vu.cs.softwaredesign.services.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import nl.vu.cs.softwaredesign.domain.core.UserProfile;
import nl.vu.cs.softwaredesign.domain.plan.TrainingPlan;
import nl.vu.cs.softwaredesign.domain.tracking.RecoveryPlan;
import nl.vu.cs.softwaredesign.domain.tracking.WeightForecastResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfExporter {
    private static final Logger LOGGER = Logger.getLogger(PdfExporter.class.getName());

    public boolean export(TrainingPlan plan, UserProfile user, WeightForecastResult forecast, RecoveryPlan recovery) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("MyTrainingPlan.pdf"));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("Fitness Training Plan", titleFont));
            document.add(new Paragraph("\n--- User Profile ---"));
            document.add(new Paragraph("Current Weight: " + user.getWeightKg() + " kg"));
            document.add(new Paragraph("Primary Goal: " + user.getGoal().getGoalType()));

            document.add(new Paragraph("\n--- Recovery Status ---"));
            document.add(new Paragraph(recovery.getRecommendation()));

            document.add(new Paragraph("\n--- Weekly Schedule ---"));

            if (plan.getWeekPlans().isEmpty()) {
                document.add(new Paragraph("No workouts scheduled. Check equipment constraints."));
            } else {
                for (nl.vu.cs.softwaredesign.domain.plan.WeekPlan week : plan.getWeekPlans()) {
                    document.add(new Paragraph("\n=== WEEK " + week.getWeekNumber() + " ==="));

                    for (nl.vu.cs.softwaredesign.domain.plan.DayOfTraining day : week.getDaysOfTraining()) {
                        document.add(new Paragraph("\n" + day.getDay() + " WORKOUT:"));
                        for (nl.vu.cs.softwaredesign.domain.plan.PlannedExercise plannedEx : day.getExercises()) {
                            document.add(new Paragraph(" - " + plannedEx.getExercise().getName() +
                                    ": " + plannedEx.getSets() + " sets of " + plannedEx.getReps() +
                                    " reps (Rest: " + plannedEx.getRestInSeconds() + "s)"));
                        }
                    }
                }
            }

            document.add(new Paragraph("\n--- AI Weight Forecast ---"));
            document.add(new Paragraph("Starting Weight: " + user.getWeightKg() + " kg"));
            document.add(new Paragraph("Predicted Weight in " + user.getGoal().getTargetWeeks() + " weeks: " + forecast.getFinalPredictedWeight() + " kg"));

            document.close();
            return true;

        } catch (DocumentException | IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create PDF document", e);
            return false;
        }
    }
}