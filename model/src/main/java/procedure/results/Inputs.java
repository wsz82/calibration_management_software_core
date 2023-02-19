package procedure.results;

import java.util.List;

public record Inputs(
        List<Double> referencedValues,
        List<Double> checkedValues
) {

    public void addReferenceValue(double value) {
        referencedValues.add(value);
    }

    public void addCheckedValue(double value) {
        checkedValues.add(value);
    }

}
