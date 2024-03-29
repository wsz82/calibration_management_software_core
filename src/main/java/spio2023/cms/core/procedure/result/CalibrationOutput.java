package spio2023.cms.core.procedure.result;

import lombok.Data;
import spio2023.cms.core.unit.ControlPoint;
import spio2023.cms.core.unit.MeasurementType;

import java.util.Map;

@Data
public class CalibrationOutput {
    private final Map<MeasurementType, Map<ControlPoint, Result>> controlPointToResults;

    public boolean isPass() {
        return controlPointToResults.values().stream()
                .flatMap(f -> f.values().stream())
                .allMatch(Result::isPass);
    }

}
