package engine;

import instruction.Instruction;
import instruction.step.Results;
import scope.Scope;

import java.util.TreeMap;

public interface CalibrationEngine {

    TreeMap<Scope, Results> runCalibration(Instruction instruction);

}
