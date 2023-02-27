package procedure.step;

public class InputsStep extends Step {
    private final String message;
    private final double controlPoint;

    public InputsStep(String message, double controlPoint) {
        this.message = message;
        this.controlPoint = controlPoint;
    }

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {
        var measurementsNumber = state.settings().measurementsNumber();
        for (int number = 1; number <= measurementsNumber; number++) {
            addInput();
        }
    }

    private void addInput() {
        var referencedInput = stepInterface.getReferencedInput();
        var checkedInput = stepInterface.getCheckedInput();
        var results = state.controlPointToInputs().get(controlPoint);
        results.addReferenceValue(referencedInput);
        results.addCheckedValue(checkedInput);
        stepInterface.showMessage(referencedInput + "; " + checkedInput);
    }

    @Override
    public StepType getStepType() {
        return StepType.INPUT;
    }


}
