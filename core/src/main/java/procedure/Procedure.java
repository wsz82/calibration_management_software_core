package procedure;

import device.TestDevice;
import lombok.Data;
import procedure.step.Step;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public class Procedure {
    private final Settings settings;
    private final TestDevice testDevice;
    private final Map<MeasurementType, List<ControlPoint>> controlPoints;
    private final List<Step> steps;
}
