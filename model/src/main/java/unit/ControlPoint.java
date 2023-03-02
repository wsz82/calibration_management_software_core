package unit;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public record ControlPoint(
        Parameter... parameters
) {

    public double basicValue() {
        return parameters[0].value();
    }

    public Prefix basicPrefix() {
        return parameters[0].prefix();
    }

    public ControlPoint(double value) {
        this(new Parameter(value));
    }

    public ControlPoint(double... values) {
        this(DoubleStream.of(values).mapToObj(Parameter::new).toArray(Parameter[]::new));
    }

    public ControlPoint(Prefix prefix,  double value) {
        this(new Parameter(prefix, value));
    }

    @Override
    public String toString() {
        return "ControlPoint[" +
                "parameters=" + Arrays.toString(parameters) +
                ']';
    }

}
