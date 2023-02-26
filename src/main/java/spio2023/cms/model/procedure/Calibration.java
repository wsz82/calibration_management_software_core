package spio2023.cms.model.procedure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import spio2023.cms.model.device.TestDevice;
import spio2023.cms.model.instrument.ReferenceInstrument;
import spio2023.cms.model.procedure.result.Result;
import spio2023.cms.model.unit.ControlPoint;
import spio2023.cms.model.unit.MeasurementType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Calibration {
    private final Setting setting;
    private final ReferenceInstrument referenceInstrument;
    private final TestDevice testDevice;
    private final Map<MeasurementType, List<ControlPoint>> controlPointsData;
    private final Map<MeasurementType, Map<ControlPoint, Result>> resultsData;

    public Calibration(
            Setting setting,
            ReferenceInstrument referenceInstrument,
            TestDevice testDevice,
            Map<MeasurementType, List<ControlPoint>> controlPoints
    ) {
        this(setting, referenceInstrument, testDevice, controlPoints, new HashMap<>());
    }

    public void addResults(MeasurementType measurementType, ControlPoint controlPoint, Result result) {
        resultsData.computeIfAbsent(measurementType, k -> new HashMap<>())
                .put(controlPoint, result);
    }

}
