package spio2023.cms.core.engine;

import spio2023.cms.core.procedure.Calibration;
import spio2023.cms.core.procedure.step.Step;

public interface StepCalibrationEngine {
    void runCalibration(Step step, Calibration state);

}
