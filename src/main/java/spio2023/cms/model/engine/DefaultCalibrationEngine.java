package spio2023.cms.model.engine;

import spio2023.cms.model.instrument.ReferenceInstrument;
import spio2023.cms.model.procedure.Calibration;
import spio2023.cms.model.procedure.Procedure;
import spio2023.cms.model.procedure.StepInterface;
import spio2023.cms.model.procedure.result.CalibrationOutput;
import spio2023.cms.model.procedure.step.Step;

public class DefaultCalibrationEngine implements CalibrationEngine {
    private final StepInterface stepInterface;

    public DefaultCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public CalibrationOutput runCalibration(Procedure procedure, ReferenceInstrument instrument) {
        var calibration = new Calibration(procedure.getSetting(), instrument, procedure.getTestDevice(), procedure.getControlPoints());
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
