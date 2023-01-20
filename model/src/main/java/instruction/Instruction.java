package instruction;

import device.ReferenceDevice;
import instruction.step.Step;

import java.util.List;

public record Instruction(ReferenceDevice referenceDevice, List<Step> steps) {

}
