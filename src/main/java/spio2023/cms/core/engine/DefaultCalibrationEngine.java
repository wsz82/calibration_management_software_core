package spio2023.cms.core.engine;

import spio2023.cms.core.procedure.Calibration;
import spio2023.cms.core.procedure.Procedure;
import spio2023.cms.core.procedure.result.CalibrationOutput;
import spio2023.cms.core.procedure.step.Step;

public class DefaultCalibrationEngine implements CalibrationEngine {
    private final StepCalibrationEngine stepCalibrationEngine;

    public DefaultCalibrationEngine(StepCalibrationEngine stepCalibrationEngine) {
        this.stepCalibrationEngine = stepCalibrationEngine;
    }

    @Override
    public CalibrationOutput runCalibration(Procedure procedure) {
        var calibration = new Calibration(procedure.getSetting(), procedure.getReferenceInstrument(), procedure.getTestDevice(), procedure.getControlPoints());
        for (Step step : procedure.getSteps()) {
            stepCalibrationEngine.runCalibration(step, calibration);
        }
        var controlPointToResults = calibration.getResultsData();
        return new CalibrationOutput(controlPointToResults);
    }

}
