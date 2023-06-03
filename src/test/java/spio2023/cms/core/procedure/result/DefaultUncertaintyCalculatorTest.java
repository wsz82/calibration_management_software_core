package spio2023.cms.core.procedure.result;
import spio2023.cms.core.instrument.AccuracyPattern;
import spio2023.cms.core.procedure.result.Input;
import org.junit.Test;
import spio2023.cms.core.unit.Prefix;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultUncertaintyCalculatorTest {

    private final double delta = 0.0001;

    @Test
    public void test_calculate_with_simple_values() {
        var referencedValues = Arrays.asList(-15.01, -15.00, -14.98, -14.99, -15.00);
        var checkedValues = Arrays.asList(-16.4, -16.1, -16.7, -16.4, -16.3);
        var inputs = new Input(referencedValues, checkedValues);
        var accuracyPattern = new AccuracyPattern(0.005, 4);
        var calculator = new DefaultUncertaintyCalculator(-2, accuracyPattern, 2);

        var results = calculator.calculate(Prefix.NULL,inputs);

        assertEquals(referencedValues, results.getInput().getReferenceValues());
        assertEquals(checkedValues, results.getInput().getTestValues());
        assertEquals(-14.995999999999999, results.getMeanReferenceValue(), delta);
        assertEquals(-16.38, results.getMeanTestValue(), delta);
        assertEquals(1.3840000000000003, results.getError(), delta);
        assertEquals(0.0969535971483262, results.getUncertaintyA(), delta);
        assertEquals(0.002886751345948129, results.getUncertaintyB(), delta);
        assertEquals(2.266111353574665, results.getUncertaintyC(), delta);
        assertEquals(4.5, results.getUncertainty(), delta);
        assertEquals(-21.5, results.getLowerBoundary(), delta);
        assertEquals(-8.5, results.getUpperBoundary(), delta);
        assertTrue(results.isPass());
    }

    @Test
    public void test_calculate_with_single_values() {
        var referencedValues = List.of(15.00);
        var checkedValues = List.of(15.1);
        var inputs = new Input(referencedValues, checkedValues);
        var accuracyPattern = new AccuracyPattern(0.005, 4);
        var calculator = new DefaultUncertaintyCalculator(-2, accuracyPattern, 2);

        var results = calculator.calculate(Prefix.NULL,inputs);

        assertEquals(referencedValues, results.getInput().getReferenceValues());
        assertEquals(checkedValues, results.getInput().getTestValues());
        assertEquals(15.0, results.getMeanReferenceValue(), delta);
        assertEquals(15.1, results.getMeanTestValue(), delta);
        assertEquals(-0.09999999999999964, results.getError(), delta);
        assertEquals(0.0, results.getUncertaintyA(), delta);
        assertEquals(0.002886751345948129, results.getUncertaintyB(), delta);
        assertEquals(2.352702346947725, results.getUncertaintyC(), delta);
        assertEquals(4.7, results.getUncertainty(), delta);
        assertEquals(8.3, results.getLowerBoundary(), delta);
        assertEquals(21.7, results.getUpperBoundary(), delta);
        assertTrue(results.isPass());
    }

    @Test
    public void test_calculate_with_empty_values() {
        var inputs = new Input(
                List.of(),
                List.of()
        );
        var accuracyPattern = new AccuracyPattern(0, 0);
        var calculator = new DefaultUncertaintyCalculator(0, accuracyPattern, 0);

        var results = calculator.calculate(Prefix.NULL,inputs);

        assertEquals(Double.NaN, results.getMeanReferenceValue(), delta);
        assertEquals(Double.NaN, results.getMeanTestValue(), delta);
        assertEquals(Double.NaN, results.getError(), delta);
        assertEquals(0.0, results.getUncertaintyA(), delta);
        assertEquals(0.2886751345948129, results.getUncertaintyB(), delta);
        assertEquals(Double.NaN, results.getUncertaintyC(), delta);
        assertEquals(0.0, results.getUncertainty(), delta);
        assertEquals(0.0, results.getLowerBoundary(), delta);
        assertEquals(0.0, results.getUpperBoundary(), delta);
        assertFalse(results.isPass());
    }

}