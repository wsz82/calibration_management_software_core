package procedure.step;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import procedure.Calibration;
import procedure.StepInterface;

@Getter
@Setter
@NoArgsConstructor
public abstract class Step {
    protected Calibration state;
    protected StepInterface stepInterface;

    public abstract void show();

    public abstract void execute();

    public abstract StepType getStepType();

}
