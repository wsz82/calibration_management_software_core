package procedure.results;

import unit.Prefix;

public record Results(
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

}
