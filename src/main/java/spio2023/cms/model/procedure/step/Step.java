package spio2023.cms.model.procedure.step;

import lombok.Data;
import spio2023.cms.model.procedure.Calibration;
import spio2023.cms.model.procedure.StepInterface;

@Data
public abstract class Step {
    protected Calibration state;
    protected StepInterface stepInterface;

    public abstract void show();

    public abstract void execute();

}
