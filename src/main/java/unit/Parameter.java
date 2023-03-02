package unit;

public record Parameter(
        Prefix prefix,
        double value
) {

    public Parameter(double value) {
        this(Prefix.NULL, value);
    }

    public double normalizedValue() {
        return value * prefix.multiplicand();
    }

}
