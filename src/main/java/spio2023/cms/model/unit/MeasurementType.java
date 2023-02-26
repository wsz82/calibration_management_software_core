package spio2023.cms.model.unit;

import lombok.Data;

@Data
public class MeasurementType {
    private final String name;
    private final String symbol;
    private final String[] units;

    public MeasurementType(String name, String symbol, String... units) {
        this.name = name;
        this.symbol = symbol;
        this.units = units;
    }
}
