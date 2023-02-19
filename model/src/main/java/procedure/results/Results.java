package procedure.results;

public record Results(
        Inputs inputs,
        double meanReferencedValue,
        double meanCheckedValue,
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
