package procedure.results;

import lombok.Data;
import unit.Prefix;

@Data
public final class Results {
    private final Prefix prefix;
    private final Inputs inputs;
    private final double meanReferenceValue;
    private final double meanTestValue;
    private final double error;
    private final double uncertaintyA;
    private final double uncertaintyB;
    private final double uncertaintyC;
    private final double uncertainty;
    private final double lowerBoundary;
    private final double upperBoundary;
    private final boolean pass;

    public Results(
            Prefix prefix,
            Inputs inputs,
            double meanReferenceValue,
            double meanTestValue,
            double error,
            double uncertaintyA,
            double uncertaintyB,
            double uncertaintyC,
            double uncertainty,
            double lowerBoundary,
            double upperBoundary,
            boolean pass
    ) {
        this.prefix = prefix;
        this.inputs = inputs;
        this.meanReferenceValue = meanReferenceValue;
        this.meanTestValue = meanTestValue;
        this.error = error;
        this.uncertaintyA = uncertaintyA;
        this.uncertaintyB = uncertaintyB;
        this.uncertaintyC = uncertaintyC;
        this.uncertainty = uncertainty;
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.pass = pass;
    }

}
