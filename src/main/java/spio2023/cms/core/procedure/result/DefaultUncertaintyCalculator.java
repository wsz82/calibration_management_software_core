package spio2023.cms.core.procedure.result;

import spio2023.cms.core.instrument.AccuracyPattern;
import spio2023.cms.core.unit.Prefix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class DefaultUncertaintyCalculator implements UncertaintyCalculator {

    private static final double UNCERTAINTY_COVARIANT = 2;
    private final int resolutionExponent;
    private final AccuracyPattern accuracyPattern;
    private final double accuracy;

    public DefaultUncertaintyCalculator(int resolutionExponent, AccuracyPattern accuracyPattern, double accuracy) {
        this.resolutionExponent = resolutionExponent;
        this.accuracyPattern = accuracyPattern;
        this.accuracy = accuracy;
    }

    @Override
    public Result calculate(Prefix prefix, Input input) {
        var referencedValues = input.getReferenceValues();
        double meanReferencedValue = mean(referencedValues);

        var checkedValues = input.getTestValues();
        double meanCheckedValue = mean(checkedValues);

        double error = meanReferencedValue - meanCheckedValue;

        double uA = uncertaintyA(checkedValues, meanCheckedValue);
        double uB = uncertaintyB();
        double uC = uncertaintyC(meanReferencedValue);
        double uncertainty = round(combinedUncertainty(uA, uB, uC));
        int scale = scale(uncertainty);

        //todo uzgodnić z Pawłem jak obliczać AL i AU
        double AL = roundToScale((meanReferencedValue - accuracy - uncertainty), scale);
        double AU = roundToScale((meanReferencedValue + accuracy + uncertainty), scale);

        var pass = meanCheckedValue >= AL && meanCheckedValue <= AU;
        return new Result(prefix, input, meanReferencedValue, meanCheckedValue, error, uA, uB, uC, uncertainty, AL, AU, pass);
    }

    private double uncertaintyA(List<Double> checkedValues, double meanCheckedValue) {
        if (checkedValues.size() < 2) {
            return 0;
        }
        double sum = 0;
        for (double value : checkedValues) {
            sum += Math.pow(value - meanCheckedValue, 2);
        }
        double n = checkedValues.size();
        return Math.sqrt(sum / (n * (n - 1)));
    }

    private double uncertaintyB() {
        return 0.5 * Math.pow(10, resolutionExponent) / Math.sqrt(3);
    }

    private double uncertaintyC(double meanReferencedValue) {
        double accuracy = accuracyPattern.calculateAccuracy(meanReferencedValue);
        return accuracy / Math.sqrt(3);
    }

    private double combinedUncertainty(double... uncertainties) {
        double uncertaintiesSquareSum = 0;
        for (double u : uncertainties) {
            uncertaintiesSquareSum += Math.pow(u, 2);
        }
        return UNCERTAINTY_COVARIANT * Math.sqrt(uncertaintiesSquareSum);
    }

    private double round(double value) {
        try {
            var bd = new BigDecimal(value);
            bd = bd.round(new MathContext(2));
            return bd.doubleValue();
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private int scale(double value) {
        var doubleStr = Double.toString(Math.abs(value));
        var strArr = doubleStr.split("\\.");
        return strArr[1].length();
    }

    private double roundToScale(double valueToRound, int scale) {
        try {
            var decimal = new BigDecimal(valueToRound);
            decimal = decimal.setScale(scale, RoundingMode.HALF_UP);
            return decimal.doubleValue();
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private double mean(List<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
}
