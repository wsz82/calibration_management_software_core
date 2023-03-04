package spio2023.calibrationmanagementsoftware.api.model.procedure;

import lombok.Data;
import spio2023.calibrationmanagementsoftware.api.model.device.TestDevice;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.Step;
import spio2023.calibrationmanagementsoftware.api.model.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.model.unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public class Procedure {
    private final Settings settings;
    private final TestDevice testDevice;
    private final Map<MeasurementType, List<ControlPoint>> controlPoints;
    private final List<Step> steps;
}
