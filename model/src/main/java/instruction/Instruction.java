package instruction;

import device.Device;
import instruction.step.Step;

import java.util.List;

public record Instruction(Device referenceDevice, Device checkedDevice, List<Double> controlPoints, List<Step> steps) {

}
