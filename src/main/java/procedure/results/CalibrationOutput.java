package procedure.results;

import unit.ControlPoint;
import unit.MeasurementType;

import java.util.Map;

public record CalibrationOutput(
        Map<MeasurementType, Map<ControlPoint, Results>> controlPointToResults
) {

    public boolean isPass() {
        return controlPointToResults.values().stream()
                .flatMap(f -> f.values().stream())
                .allMatch(Results::pass);
    }

}
