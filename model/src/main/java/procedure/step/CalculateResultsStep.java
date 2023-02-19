package procedure.step;

import procedure.results.DefaultUncertaintyCalculator;
import scope.AccuracyScope;

public class CalculateResultsStep extends Step {

    private final String message;
    private final AccuracyScope accuracyScope;
    private final double controlPoint;

    public CalculateResultsStep(String message, AccuracyScope accuracyScope, double controlPoint) {
        this.message = message;
        this.accuracyScope = accuracyScope;
        this.controlPoint = controlPoint;
    }

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {
        var resolutionExponent = state.referenceDevice().resolutionExponent();
        var accuracyPattern = state.referenceDevice().accuracyPattern();
        var inputs = state.controlPointToInputs().get(controlPoint);
        var accuracy = accuracyScope.accuracy();
        var calculator = new DefaultUncertaintyCalculator(resolutionExponent, accuracyPattern, accuracy);
        var results = calculator.calculate(inputs);
        state.controlPointToResults().put(controlPoint, results);
    }

    @Override
    public StepType getStepType() {
        return StepType.RESULTS;
    }


}
