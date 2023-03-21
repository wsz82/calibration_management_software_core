package spio2023.cms.core.engine;

import spio2023.cms.core.procedure.Calibration;
import spio2023.cms.core.procedure.StepInterface;
import spio2023.cms.core.procedure.step.Step;

public class DefaultStepCalibrationEngine implements StepCalibrationEngine {
    private final StepInterface stepInterface;

    public DefaultStepCalibrationEngine(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }

    @Override
    public void runCalibration(Step step, Calibration state) {
        step.setState(state);
        step.setStepInterface(stepInterface);
        step.show();
        step.execute();
    }
}
