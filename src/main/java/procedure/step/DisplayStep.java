package procedure.step;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DisplayStep extends Step {
    private final String message;

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {}

    @Override
    public StepType getStepType() {
        return StepType.DISPLAY;
    }

}
