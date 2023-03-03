package engine;

import instrument.ReferenceInstrument;
import procedure.Calibration;
import procedure.Procedure;
import procedure.StepInterface;
import procedure.results.CalibrationOutput;
import procedure.step.Step;

public class DefaultCalibrationEngine implements CalibrationEngine {
    private final StepInterface stepInterface;

    public DefaultCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public CalibrationOutput runCalibration(Procedure procedure, ReferenceInstrument instrument) {
        var calibration = new Calibration(procedure.getSettings(), instrument, procedure.getTestDevice(), procedure.getControlPoints());
        for (Step step : procedure.getSteps()) {
            step.setState(calibration);
            step.setStepInterface(stepInterface);
            step.show();
            step.execute();
        }
        var controlPointToResults = calibration.getResultsData();
        return new CalibrationOutput(controlPointToResults);
    }

}
