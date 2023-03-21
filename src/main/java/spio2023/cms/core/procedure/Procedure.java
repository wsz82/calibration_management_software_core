package spio2023.cms.core.procedure;

import lombok.Data;
import spio2023.cms.core.device.TestDevice;
import spio2023.cms.core.instrument.ReferenceInstrument;
import spio2023.cms.core.procedure.step.Step;
import spio2023.cms.core.unit.ControlPoint;
import spio2023.cms.core.unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public class Procedure {
    private final Setting setting;
    private final TestDevice testDevice;
    private final ReferenceInstrument referenceInstrument;
    private final Map<MeasurementType, List<ControlPoint>> controlPoints;
    private final List<Step> steps;
}
