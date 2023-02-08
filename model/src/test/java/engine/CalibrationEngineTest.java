package engine;

import device.Device;
import device.ReferenceDevice;
import instruction.Instruction;
import instruction.step.CalculateDeviationStep;
import instruction.step.DisplayStep;
import instruction.step.InputStep;
import org.junit.Test;
import scope.Scope;
import scope.ScopesList;
import unit.Unit;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class CalibrationEngineTest {

    @Test
    public void thermometer_calibration() {
        var lowScope = new Scope(BigDecimal.valueOf(-30), BigDecimal.valueOf(-10));
        var mediumScope = new Scope(BigDecimal.valueOf(-10), BigDecimal.valueOf(10));
        var highScope = new Scope(BigDecimal.valueOf(10), BigDecimal.valueOf(30));

        var device = new Device("RTĘCIOWY_1");
        var tolerance = BigDecimal.valueOf(5);
        var celsiusUnit = new Unit("Celsius", "°C");
        var scopesList = new ScopesList(celsiusUnit, List.of(lowScope, mediumScope, highScope));
        var referenceDevice = new ReferenceDevice(device, tolerance, scopesList);

        var instruction = new Instruction(referenceDevice, Arrays.asList(
                new DisplayStep("Kalibracja termometru rtęciowego"),
                new DisplayStep("Włącz lodówkę i ustaw temperaturę na -20"),
                new InputStep("Zmierz temperaturę termometrem wzorcowym: ",
                        lowScope, InputStep.DeviceType.REFERENCED),
                new InputStep("Zmierz temperaturę termometrem sprawdzanym: ",
                        lowScope, InputStep.DeviceType.CHECKED),
                new CalculateDeviationStep("", lowScope),
                new DisplayStep("Ustaw temperaturę lodówki na 0"),
                new InputStep("Zmierz temperaturę termometrem wzorcowym: ",
                        mediumScope, InputStep.DeviceType.REFERENCED),
                new InputStep("Zmierz temperaturę termometrem sprawdzanym: ",
                        mediumScope, InputStep.DeviceType.CHECKED),
                new CalculateDeviationStep("", mediumScope),
                new DisplayStep("Włącz piec i ustaw temperaturę na 20"),
                new InputStep("Zmierz temperaturę termometrem wzorcowym: ",
                        highScope, InputStep.DeviceType.REFERENCED),
                new InputStep("Zmierz temperaturę termometrem sprawdzanym: ",
                        highScope, InputStep.DeviceType.CHECKED),
                new CalculateDeviationStep("", highScope),
                new DisplayStep("Kalibracja zakończona")
        ));

        var nextInputs = new Stack<BigDecimal>();
        var lowReferencedValue = BigDecimal.valueOf(-20);
        var lowCheckedValue = BigDecimal.valueOf(-21);
        var mediumReferencedValue = BigDecimal.valueOf(0.1);
        var mediumCheckedValue = BigDecimal.valueOf(0.11);
        var highReferencedValue = BigDecimal.valueOf(19.97);
        var highCheckedValue = BigDecimal.valueOf(22);
        var inputs = Arrays.asList(
                lowReferencedValue, lowCheckedValue,
                mediumReferencedValue, mediumCheckedValue,
                highReferencedValue, highCheckedValue
        );
        Collections.reverse(inputs);
        nextInputs.addAll(inputs);

        var calibrationEngine = new StandardCalibrationEngine(System.out::println, nextInputs::pop);
        var scopeToResults = calibrationEngine.runCalibration(instruction);

        scopeToResults.forEach((scope, results) -> {
            System.out.println("---");
            System.out.println(scope);
            System.out.println(results);
        });
        var lowScopeResult = scopeToResults.get(lowScope).deviationPercentResult().getValue();
        assertEquals(new BigDecimal("5.0000"), lowScopeResult);
        var mediumScopeResult = scopeToResults.get(mediumScope).deviationPercentResult().getValue();
        assertEquals(new BigDecimal("10.0000"), mediumScopeResult);
        var highScopeResult = scopeToResults.get(highScope).deviationPercentResult().getValue();
        assertEquals(new BigDecimal("10.1600"), highScopeResult);
    }


}