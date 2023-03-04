package spio2023.calibrationmanagementsoftware.api.model.procedure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import spio2023.calibrationmanagementsoftware.api.model.device.TestDevice;
import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.model.procedure.results.Results;
import spio2023.calibrationmanagementsoftware.api.model.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.model.unit.MeasurementType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Calibration {
    private final Settings settings;
    private final ReferenceInstrument referenceInstrument;
    private final TestDevice testDevice;
    private final Map<MeasurementType, List<ControlPoint>> controlPointsData;
    private final Map<MeasurementType, Map<ControlPoint, Results>> resultsData;

    public Calibration(
            Settings settings,
            ReferenceInstrument referenceInstrument,
            TestDevice testDevice,
            Map<MeasurementType, List<ControlPoint>> controlPoints
    ) {
        this(settings, referenceInstrument, testDevice, controlPoints, new HashMap<>());
    }

    public void addResults(MeasurementType measurementType, ControlPoint controlPoint, Results results) {
        resultsData.computeIfAbsent(measurementType, k -> new HashMap<>())
                .put(controlPoint, results);
    }

}
