package spio2023.cms.model.procedure.result;

import lombok.Data;
import spio2023.cms.model.unit.ControlPoint;
import spio2023.cms.model.unit.MeasurementType;

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
