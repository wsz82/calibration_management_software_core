package instruction.step;

public class DisplayStep extends Step {
    private final String message;

    public DisplayStep(String message) {
        this.message = message;
    }

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {

    }

    @Override
    public StepType getStepType() {
        return StepType.DISPLAY;
    }


}
