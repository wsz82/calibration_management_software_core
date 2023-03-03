package engine;

import procedure.Calibration;
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
        var calibrationState = new Calibration(settings, procedure.getReferenceInstrument(), procedure.getTestDevice(), procedure.getControlPoints());
        for (Step step : procedure.getSteps()) {
            step.setState(calibrationState);
            step.setStepInterface(stepInterface);
            step.show();
            step.execute();
        }
        var controlPointToResults = calibrationState.getResultsData();
        return new CalibrationOutput(controlPointToResults);
    }

}
