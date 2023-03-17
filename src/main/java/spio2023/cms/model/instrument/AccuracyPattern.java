package spio2023.cms.model.instrument;

import lombok.Data;

@Data
public class AccuracyPattern {
    private final double part;
    private final double constant;

    public double calculateAccuracy(double measuredValue) {
        return (part * measuredValue) + constant;
    }

}
