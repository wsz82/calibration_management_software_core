package instruction.step;

import scope.Scope;

public class InputStep extends Step {
    private final String message;
    private final Scope scope;
    private final DeviceType deviceType;

    public InputStep(String message, Scope scope, DeviceType deviceType) {
        this.message = message;
        this.scope = scope;
        this.deviceType = deviceType;
    }

    @Override
    public void show() {
        messageConsumer.accept(message);
    }

    @Override
    public void execute() {
        var value = inputSupplier.get();
        var results = state.scopeToResults().get(scope);
        if (deviceType == DeviceType.REFERENCED) {
            results.referencedValue().setValue(value);
        } else if (deviceType == DeviceType.CHECKED) {
            results.checkedValue().setValue(value);
        } else {
            throw new IllegalStateException("DeviceType not recognized: " + deviceType);
        }
        messageConsumer.accept(value.toString());
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
