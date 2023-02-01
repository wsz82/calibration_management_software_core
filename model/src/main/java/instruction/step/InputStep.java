package instruction.step;

public class InputStep extends Step {
    private final String message;
    private final double controlPoint;
    private final DeviceType deviceType;

    public InputStep(String message, double controlPoint, DeviceType deviceType) {
        this.message = message;
        this.controlPoint = controlPoint;
        this.deviceType = deviceType;
    }

    @Override
    public void show() {
        messageConsumer.accept(message);
    }

    @Override
    public void execute() {
        var value = inputSupplier.get();
        var results = state.controlPointToResults().get(controlPoint);
        if (deviceType == DeviceType.REFERENCED) {
            results.addReferenceValue(value);
        } else if (deviceType == DeviceType.CHECKED) {
            results.addCheckedValue(value);
        } else {
            throw new IllegalStateException("DeviceType not recognized: " + deviceType);
        }
        messageConsumer.accept(String.valueOf(value));
    }

    @Override
    public StepType getStepType() {
        return StepType.INPUT;
    }

    public enum DeviceType {
        REFERENCED,
        CHECKED
    }


}
