package procedure;

import device.Device;
import procedure.step.Step;

import java.util.List;

public record Procedure(Device referenceDevice, Device checkedDevice, List<Double> controlPoints, List<Step> steps) {

}
