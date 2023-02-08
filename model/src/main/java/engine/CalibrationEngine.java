package engine;

import instruction.Instruction;
import instruction.Settings;
import instruction.step.Results;

import java.util.TreeMap;

public interface CalibrationEngine {

    TreeMap<Double, Results> runCalibration(Instruction instruction, Settings settings);

}
