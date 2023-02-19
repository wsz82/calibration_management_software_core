package procedure.results;

import device.AccuracyPattern;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultUncertaintyCalculatorTest {

    private final double delta = 0.0001;

    @Test
    public void test_calculate_with_simple_values() {
        var referencedValues = Arrays.asList(-15.01, -15.00, -14.98, -14.99, -15.00);
        var checkedValues = Arrays.asList(-16.4, -16.1, -16.7, -16.4, -16.3);
        var inputs = new Inputs(referencedValues, checkedValues);
        var accuracyPattern = new AccuracyPattern(0.005, 4);
        var calculator = new DefaultUncertaintyCalculator(-2, accuracyPattern, 2);

        var results = calculator.calculate(inputs);

        assertEquals(referencedValues, results.inputs().referencedValues());
        assertEquals(checkedValues, results.inputs().checkedValues());
        assertEquals(-14.995999999999999, results.meanReferencedValue(), delta);
        assertEquals(-16.38, results.meanCheckedValue(), delta);
        assertEquals(1.3840000000000003, results.error(), delta);
        assertEquals(0.0969535971483262, results.uncertaintyA(), delta);
        assertEquals(0.002886751345948129, results.uncertaintyB(), delta);
        assertEquals(2.266111353574665, results.uncertaintyC(), delta);
        assertEquals(4.536372559714792, results.uncertainty(), delta);
        assertEquals(-21.53237255971479, results.lowerBoundary(), delta);
        assertEquals(-8.459627440285207, results.upperBoundary(), delta);
        assertTrue(results.pass());
    }

    @Test
    public void test_calculate_with_single_values() {
        var referencedValues = List.of(15.00);
        var checkedValues = List.of(15.1);
        var inputs = new Inputs(referencedValues, checkedValues);
        var accuracyPattern = new AccuracyPattern(0.005, 4);
        var calculator = new DefaultUncertaintyCalculator(-2, accuracyPattern, 2);

        var results = calculator.calculate(inputs);

        assertEquals(referencedValues, results.inputs().referencedValues());
        assertEquals(checkedValues, results.inputs().checkedValues());
        assertEquals(15.0, results.meanReferencedValue(), delta);
        assertEquals(15.1, results.meanCheckedValue(), delta);
        assertEquals(-0.09999999999999964, results.error(), delta);
        assertEquals(0.0, results.uncertaintyA(), delta);
        assertEquals(0.002886751345948129, results.uncertaintyB(), delta);
        assertEquals(2.352702346947725, results.uncertaintyC(), delta);
        assertEquals(4.705408235920308, results.uncertainty(), delta);
        assertEquals(8.29459176407969, results.lowerBoundary(), delta);
        assertEquals(21.70540823592031, results.upperBoundary(), delta);
        assertTrue(results.pass());
    }

    @Test
    public void test_calculate_with_empty_values() {
        var inputs = new Inputs(
                List.of(),
                List.of()
        );
        var accuracyPattern = new AccuracyPattern(0, 0);
        var calculator = new DefaultUncertaintyCalculator(0, accuracyPattern, 0);

        var results = calculator.calculate(inputs);

        assertEquals(Double.NaN, results.meanReferencedValue(), delta);
        assertEquals(Double.NaN, results.meanCheckedValue(), delta);
        assertEquals(Double.NaN, results.error(), delta);
        assertEquals(0.0, results.uncertaintyA(), delta);
        assertEquals(0.2886751345948129, results.uncertaintyB(), delta);
        assertEquals(Double.NaN, results.uncertaintyC(), delta);
        assertEquals(Double.NaN, results.uncertainty(), delta);
        assertEquals(Double.NaN, results.lowerBoundary(), delta);
        assertEquals(Double.NaN, results.upperBoundary(), delta);
        assertFalse(results.pass());
    }

}