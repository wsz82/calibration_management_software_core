package procedure;

import device.TestedDevice;
import instrument.ReferenceInstrument;
import procedure.step.Step;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

public record Procedure(
        ReferenceInstrument referenceInstrument,
        TestedDevice testedDevice,
        Map<MeasurementType, List<ControlPoint>> measurementTypes,
        List<Step> steps
) {

}
