package instruction.step;

import instruction.CalibrationState;
import instruction.StepInterface;

public abstract class Step {
    protected CalibrationState state;
    protected StepInterface stepInterface;

    public Step() {}

    public abstract void show();

    public abstract void execute();

    public abstract StepType getStepType();

    public void setState(CalibrationState state) {
        this.state = state;
    }

    public void setStepInterface(StepInterface stepInterface) {
        this.stepInterface = stepInterface;
    }
}
