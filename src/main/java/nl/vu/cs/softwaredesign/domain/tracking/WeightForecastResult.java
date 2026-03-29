package nl.vu.cs.softwaredesign.domain.tracking;

import java.util.List;


// Created the WeightForecastResult class to encapsulate the results of a weight forecast,
// including the final predicted weight and a list of weekly weights leading up to that prediction.
// This class provides getter methods to access these attributes,
// allowing other parts of the application to utilize the forecast results effectively.
public class WeightForecastResult {
    private final double finalPredictedWeight;
    private final List<Double> weeklyWeights;

    public WeightForecastResult(double finalPredictedWeight, List<Double> weeklyWeights) {
        this.finalPredictedWeight = finalPredictedWeight;
        this.weeklyWeights = weeklyWeights;
    }

    public double getFinalPredictedWeight() { return finalPredictedWeight; }
    public List<Double> getWeeklyWeights() { return weeklyWeights; }
}