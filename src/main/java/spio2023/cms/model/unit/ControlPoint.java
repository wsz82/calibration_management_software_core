package spio2023.cms.model.unit;

import lombok.Data;

import java.util.stream.DoubleStream;

@Data
public class ControlPoint {
    private final Parameter[] parameters;

    public ControlPoint(Parameter... parameters) {
        this.parameters = parameters;
    }

    public ControlPoint(double value) {
        this(new Parameter(value));
    }

    public ControlPoint(double... values) {
        this(DoubleStream.of(values).mapToObj(Parameter::new).toArray(Parameter[]::new));
    }

    public ControlPoint(Prefix prefix, double value) {
        this(new Parameter(prefix, value));
    }

    public double basicValue() {
        return parameters[0].getValue();
    }

    public Prefix basicPrefix() {
        return parameters[0].getPrefix();
    }

}
