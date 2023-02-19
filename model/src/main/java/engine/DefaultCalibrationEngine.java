package engine;

import procedure.CalibrationState;
import procedure.Procedure;
import procedure.Settings;
import procedure.StepInterface;
import procedure.results.CalibrationOutput;
import procedure.step.Step;

public class DefaultCalibrationEngine implements CalibrationEngine {
    private final StepInterface stepInterface;

    public DefaultCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public CalibrationOutput runCalibration(Procedure procedure, Settings settings) {
        var calibrationState = new CalibrationState(procedure.referenceDevice(), procedure.checkedDevice(),
                procedure.controlPoints(), settings);
        for (Step step : procedure.steps()) {
            step.setState(calibrationState);
            step.setStepInterface(stepInterface);
            step.show();
            step.execute();
        }
        var controlPointToResults = calibrationState.controlPointToResults();
        return new CalibrationOutput(controlPointToResults);
    }


}
