package spio2023.cms.model.unit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Parameter {
    private final Prefix prefix;
    private final double value;

    public Parameter(double value) {
        this(Prefix.NULL, value);
    }

    public double normalizedValue() {
        return value * prefix.multiplicand();
    }

}

