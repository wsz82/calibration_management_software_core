package instrument;

public record AccuracyPattern(
        double part,
        double constant
) {

    public double calculateAccuracy(double measuredValue) {
        return (part * measuredValue) + constant;
    }

}
