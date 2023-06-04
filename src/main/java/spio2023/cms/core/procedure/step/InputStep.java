package spio2023.cms.core.procedure.step;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spio2023.cms.core.procedure.result.DefaultUncertaintyCalculator;
import spio2023.cms.core.procedure.result.Input;
import spio2023.cms.core.procedure.result.Result;
import spio2023.cms.core.unit.ControlPoint;
import spio2023.cms.core.unit.MeasurementType;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = true)
public class InputStep extends Step {
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

    private Input getInputs() {
        var referenceInput = getReferenceInputs();
        var testInput = stepInterface.getCheckedInput();
        return new Input(Arrays.asList(referenceInput), Arrays.asList(testInput));
    }

    private Double[] getReferenceInputs() {
        return state.getSetting().isReferenceValuesFromControlPoint()
                ? new Double[]{controlPoint.basicValue()}
                : stepInterface.getReferencedInput();
    }

    private Result calculateResults(Input input) {
        var referenceScope = state.getReferenceInstrument().getMatchingScope(measurementType, controlPoint);
        var accuracyPattern = referenceScope.getAccuracyPattern();
        var testedScope = state.getTestDevice().getMatchingScope(measurementType, controlPoint);
        var part = testedScope.getPart();
        var resolutionExponent = testedScope.getResolutionExponent();
        var digits = testedScope.getDigits();
        var overrideAccuracy = testedScope.getOverrideAccuracy();
        var calculator = new DefaultUncertaintyCalculator(overrideAccuracy, part, resolutionExponent, digits, accuracyPattern);
        return calculator.calculate(controlPoint.basicPrefix(), input);
    }

}
