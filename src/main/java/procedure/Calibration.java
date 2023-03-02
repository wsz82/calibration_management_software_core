package procedure;

import device.TestedDevice;
import instrument.ReferenceInstrument;
import procedure.results.Results;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Calibration(
        Settings settings,
        ReferenceInstrument referenceInstrument,
        TestedDevice testedDevice,
        Map<MeasurementType, List<ControlPoint>> controlPointsData,
        Map<MeasurementType, Map<ControlPoint, Results>> resultsData
) {

    public Calibration(
            Settings settings,
            ReferenceInstrument referenceInstrument,
            TestedDevice testedDevice,
            Map<MeasurementType, List<ControlPoint>> controlPoints
    ) {
        this(settings, referenceInstrument, testedDevice, controlPoints, new HashMap<>());
    }

    public void addResults(MeasurementType measurementType, ControlPoint controlPoint, Results results) {
        resultsData.computeIfAbsent(measurementType, k -> new HashMap<>())
                .put(controlPoint, results);
    }

}
