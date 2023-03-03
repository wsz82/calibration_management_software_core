package procedure;

import device.TestDevice;
import instrument.ReferenceInstrument;
import lombok.Data;
import procedure.step.Step;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public final class Procedure {
    private final ReferenceInstrument referenceInstrument;
    private final TestDevice testDevice;
    private final Map<MeasurementType, List<ControlPoint>> controlPoints;
    private final List<Step> steps;
}
