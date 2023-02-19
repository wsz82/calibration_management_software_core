package engine;

import procedure.CalibrationState;
import procedure.Procedure;
import procedure.Settings;
import procedure.StepInterface;
import procedure.step.Results;
import procedure.step.Step;

import java.util.TreeMap;

public class StandardCalibrationEngine implements CalibrationEngine {
    private final StepInterface stepInterface;

    public StandardCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public TreeMap<Double, Results> runCalibration(Procedure procedure, Settings settings) {
        var calibrationState = new CalibrationState(procedure.referenceDevice(), procedure.checkedDevice(),
                procedure.controlPoints(), settings);
        for (Step step : procedure.steps()) {
            step.setState(calibrationState);
            step.setStepInterface(stepInterface);
            step.show();
            step.execute();
        }
        return calibrationState.controlPointToResults();
    }


}
