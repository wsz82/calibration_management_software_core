package unit;

import java.util.Arrays;

public record MeasurementType(
        String name,
        String symbol,
        String... units
) {

    @Override
    public String toString() {
        return "MeasurementType[" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", units=" + Arrays.toString(units) +
                ']';
    }
}
