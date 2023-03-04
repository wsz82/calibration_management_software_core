package spio2023.calibrationmanagementsoftware.api.model.procedure.step;

import lombok.Data;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Calibration;
import spio2023.calibrationmanagementsoftware.api.model.procedure.StepInterface;

@Data
public abstract class Step {
    protected Calibration state;
    protected StepInterface stepInterface;

    public abstract void show();

    public abstract void execute();

    public abstract StepType getStepType();

}
