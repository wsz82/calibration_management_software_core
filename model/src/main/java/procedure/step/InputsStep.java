package procedure.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import procedure.results.DefaultUncertaintyCalculator;
import procedure.results.Inputs;
import procedure.results.Results;
import unit.ControlPoint;
import unit.MeasurementType;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class InputsStep extends Step {
    private final String message;
    private final MeasurementType measurementType;
    private final ControlPoint controlPoint;

    @Override
    public void show() {
        stepInterface.showMessage(message);
    }

    @Override
    public void execute() {
        var inputs = getInputs();
        var results = calculateResults(inputs);
        state.addResults(measurementType, controlPoint, results);
    }

    private Inputs getInputs() {
        var referenceInput = getReferenceInputs();
        var testInput = stepInterface.getCheckedInput();
        return new Inputs(Arrays.asList(referenceInput), Arrays.asList(testInput));
    }

    private Double[] getReferenceInputs() {
        return state.settings().referenceValuesFromControlPoint()
                ? new Double[]{controlPoint.basicValue()}
                : stepInterface.getReferencedInput();
    }

    private Results calculateResults(Inputs inputs) {
        var referenceScope = state.referenceInstrument().getMatchingScope(measurementType, controlPoint);
        var accuracyPattern = referenceScope.getAccuracyPattern();
        var testedScope = state.testedDevice().getMatchingScope(measurementType, controlPoint);
        var accuracy = testedScope.getAccuracy();
        var resolutionExponent = testedScope.getResolutionExponent();
        var calculator = new DefaultUncertaintyCalculator(resolutionExponent, accuracyPattern, accuracy);
        return calculator.calculate(controlPoint.basicPrefix(), inputs);
    }

    @Override
    public StepType getStepType() {
        return StepType.INPUTS;
    }


}
