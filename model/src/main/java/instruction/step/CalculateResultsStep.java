package instruction.step;

import scope.AccuracyScope;

import java.util.List;

public class CalculateResultsStep extends Step {
    private final String message;
    private final AccuracyScope accuracyScope;
    private final double controlPoint;

    public CalculateResultsStep(String message, AccuracyScope accuracyScope, double controlPoint) {
        this.message = message;
        this.accuracyScope = accuracyScope;
        this.controlPoint = controlPoint;
    }

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {
        calculateResults();
    }

    private void calculateResults() {
        var results = state.controlPointToResults().get(controlPoint);
        List<Double> referencedValues = results.getReadonlyReferencedValues();
        double meanReferencedValue = mean(referencedValues);
        results.setMeanReferencedValue(meanReferencedValue);

        List<Double> checkedValues = results.getReadonlyCheckedValues();
        double meanCheckedValue = mean(checkedValues);
        results.setMeanCheckedValue(meanCheckedValue);

        double error = meanReferencedValue - meanCheckedValue;
        results.setError(error);

        double u1 = uncertaintyA(checkedValues, meanCheckedValue);
        results.setUncertaintyA(u1);
        double u2 = uncertaintyB();
        results.setUncertaintyB(u2);
        double u3 = uncertaintyC(meanReferencedValue);
        results.setUncertaintyC(u3);
        double uncertainty = uncertainty(u1, u2, u3);
        results.setUncertainty(uncertainty);

        double accuracy = accuracyScope.accuracy();
        double AL = meanReferencedValue - accuracy - uncertainty;
        results.setLowerBoundary(AL);
        double AU = meanReferencedValue + accuracy + uncertainty;
        results.setUpperBoundary(AU);

        results.setPass(meanCheckedValue >= AL && meanCheckedValue <= AU);
    }

    private double uncertaintyA(List<Double> checkedValues, double meanCheckedValue) {
        double sum = 0;
        for (double value : checkedValues) {
            sum += Math.pow(value - meanCheckedValue, 2);
        }
        double n = checkedValues.size();
        return Math.sqrt(sum / (n * (n - 1)));
    }

    private double uncertaintyB() {
        return 0.5 * Math.pow(10, state.referenceDevice().resolutionExponent()) / Math.sqrt(3);
    }

    private double uncertaintyC(double meanReferencedValue) {
        double accuracy = state.referenceDevice().accuracyPattern().calculateAccuracy(meanReferencedValue);
        return accuracy / Math.sqrt(3);
    }

    private double uncertainty(double... uncertainties) {
        double uncertaintyCovariant = 2;
        double uncertaintiesSquareSum = 0;
        for (double u : uncertainties) {
            uncertaintiesSquareSum += Math.pow(u, 2);
        }
        return uncertaintyCovariant * Math.sqrt(uncertaintiesSquareSum);
    }

    private double mean(List<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    @Override
    public StepType getStepType() {
        return StepType.RESULTS;
    }


}
