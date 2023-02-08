package engine;

import instruction.CalibrationState;
import instruction.Instruction;
import instruction.Settings;
import instruction.StepInterface;
import instruction.step.Results;
import instruction.step.Step;

import java.util.TreeMap;

public class StandardCalibrationEngine implements CalibrationEngine {
    private final StepInterface stepInterface;

    public StandardCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public TreeMap<Double, Results> runCalibration(Instruction instruction, Settings settings) {
        var calibrationState = new CalibrationState(instruction.referenceDevice(), instruction.checkedDevice(),
                instruction.controlPoints(), settings);
        for (Step step : instruction.steps()) {
            step.setState(calibrationState);
            step.setStepInterface(stepInterface);
            step.show();
            step.execute();
        }
        return calibrationState.controlPointToResults();
    }


}
