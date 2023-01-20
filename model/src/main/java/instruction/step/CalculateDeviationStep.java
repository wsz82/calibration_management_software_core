package instruction.step;

import scope.Scope;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateDeviationStep extends Step {
    private final String message;
    private final Scope scope;

    public CalculateDeviationStep(String message, Scope scope) {
        this.message = message;
        this.scope = scope;
    }

    @Override
    public void show() {
        messageConsumer.accept(message);
    }

    @Override
    public void execute() {
        calculateDeviation();
    }

    private void calculateDeviation() {
        var results = state.scopeToResults().get(scope);
        var checkedValue = results.checkedValue().getValue();
        var referencedValue = results.referencedValue().getValue();

        var difference = referencedValue.subtract(checkedValue);
        var part = difference.divide(referencedValue, RoundingMode.DOWN);
        var percent = part.multiply(BigDecimal.valueOf(100)).abs();
        results.deviationPercentResult().setValue(percent);
    }

    @Override
    public StepType getStepType() {
        return StepType.DEVIATION_CALCULATION;
    }


}
