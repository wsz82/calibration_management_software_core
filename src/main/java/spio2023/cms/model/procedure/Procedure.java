package spio2023.cms.model.procedure;

import lombok.Data;
import spio2023.cms.model.device.TestDevice;
import spio2023.cms.model.instrument.ReferenceInstrument;
import spio2023.cms.model.procedure.step.Step;
import spio2023.cms.model.unit.ControlPoint;
import spio2023.cms.model.unit.MeasurementType;

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
