package procedure.results;

import lombok.Data;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.Map;

@Data
public class CalibrationOutput {
    private final Map<MeasurementType, Map<ControlPoint, Results>> controlPointToResults;

    public boolean isPass() {
        return controlPointToResults.values().stream()
                .flatMap(f -> f.values().stream())
                .allMatch(Results::isPass);
    }

}
