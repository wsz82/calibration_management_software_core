package spio2023.cms.model.procedure.step;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spio2023.cms.model.procedure.result.DefaultUncertaintyCalculator;
import spio2023.cms.model.procedure.result.Inputs;
import spio2023.cms.model.procedure.result.Result;
import spio2023.cms.model.unit.ControlPoint;
import spio2023.cms.model.unit.MeasurementType;

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

    private Inputs getInputs() {
        var referenceInput = getReferenceInputs();
        var testInput = stepInterface.getCheckedInput();
        return new Inputs(Arrays.asList(referenceInput), Arrays.asList(testInput));
    }

    private Double[] getReferenceInputs() {
        return state.getSetting().isReferenceValuesFromControlPoint()
                ? new Double[]{controlPoint.basicValue()}
                : stepInterface.getReferencedInput();
    }

    private Result calculateResults(Inputs inputs) {
        var referenceScope = state.getReferenceInstrument().getMatchingScope(measurementType, controlPoint);
        var accuracyPattern = referenceScope.getAccuracyPattern();
        var testedScope = state.getTestDevice().getMatchingScope(measurementType, controlPoint);
        var accuracy = testedScope.getAccuracy();
        var resolutionExponent = testedScope.getResolutionExponent();
        var calculator = new DefaultUncertaintyCalculator(resolutionExponent, accuracyPattern, accuracy);
        return calculator.calculate(controlPoint.basicPrefix(), inputs);
    }

}
