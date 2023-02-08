package engine;

import device.AccuracyPattern;
import device.Device;
import instruction.Instruction;
import instruction.Settings;
import instruction.StepInterface;
import instruction.step.CalculateResultsStep;
import instruction.step.DisplayStep;
import instruction.step.InputsStep;
import org.junit.Test;
import scope.AccuracyScope;
import scope.ScopesList;
import unit.Unit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class CalibrationEngineTest {

    @Test
    public void thermometer_calibration() {
        var celsius = new Unit("Celsius", "°C");

        double controlPoint1 = -15;
        double controlPoint2 = 20;
        double controlPoint3 = 50;

        var scope1 = new AccuracyScope(-20, 0, 2);
        var scope2 = new AccuracyScope(0, 40, 1);
        var scope3 = new AccuracyScope(40, 60, 2);
        var checkedScopesList = new ScopesList(celsius, List.of(scope1, scope2, scope3));
        var checkedDevice = new Device("BC06", -1, new AccuracyPattern(0.005, 4), checkedScopesList);

        var referencedScope1 = new AccuracyScope(-50, 199.99, 0.03);
        var referencedScopesList = new ScopesList(celsius, List.of(referencedScope1));
        var referenceDevice = new Device("P755", -2, new AccuracyPattern(0.005, 4), referencedScopesList);

        var controlPoints = Arrays.asList(controlPoint1, controlPoint2, controlPoint3);
        var instruction = new Instruction(referenceDevice, checkedDevice, controlPoints, Arrays.asList(
                new DisplayStep("Kalibracja termometru rtęciowego"),
                new DisplayStep("Włącz komorę klimatyczną i ustaw temperaturę na -15"),
                new DisplayStep("Zmierz naprzemiennie temperaturę termometrem wzorcowym oraz kalibrowanym"),
                new InputsStep("Pomiary wzorcowe: ", controlPoint1, InputsStep.DeviceType.REFERENCED),
                new InputsStep("Pomiary sprawdzane: ", controlPoint1, InputsStep.DeviceType.CHECKED),
                new CalculateResultsStep("Wykonanie obliczeń dla punktu -15 °C", scope1, controlPoint1),
                new DisplayStep("Ustaw temperaturę na 20"),
                new InputsStep("Pomiary wzorcowe: ", controlPoint2, InputsStep.DeviceType.REFERENCED),
                new InputsStep("Pomiary sprawdzane: ", controlPoint2, InputsStep.DeviceType.CHECKED),
                new CalculateResultsStep("Wykonanie obliczeń dla punktu 20 °C", scope2, controlPoint2),
                new DisplayStep("Ustaw temperaturę na 50"),
                new InputsStep("Pomiary wzorcowe: ", controlPoint3, InputsStep.DeviceType.REFERENCED),
                new InputsStep("Pomiary sprawdzane: ", controlPoint3, InputsStep.DeviceType.CHECKED),
                new CalculateResultsStep("Wykonanie obliczeń dla punktu 50 °C", scope3, controlPoint3),
                new DisplayStep("Kalibracja zakończona")
        ));

        var inputs = Arrays.asList(
                -15.01, -16.4,
                -15.00, -16.1,
                -14.98, -16.7,
                -14.99, -16.4,
                -15.00, -16.3,
                    20.01, 20.8,
                    20.02, 20.6,
                    20.00, 20.9,
                    19.99, 20.4,
                    19.98, 20.5,
                        50.00, 49.5,
                        50.01, 48.5,
                        50.00, 49.3,
                        49.99, 48.1,
                        50.00, 49.9
        );
        Collections.reverse(inputs);
        var nextInputs = new Stack<Double>();
        nextInputs.addAll(inputs);

        var stepInterface = new StepInterface() {
            @Override
            public void showMessage(String message) {
                System.out.println(message);
            }

            @Override
            public Double getInput() {
                return nextInputs.pop();
            }
        };
        var calibrationEngine = new StandardCalibrationEngine(stepInterface);
        var settings = new Settings(5);
        var scopeToResults = calibrationEngine.runCalibration(instruction, settings);

        scopeToResults.forEach((scope, results) -> {
            System.out.println("---");
            System.out.println(scope);
            System.out.println(results);
        });
        var results1 = scopeToResults.get(controlPoint1);
        var results2 = scopeToResults.get(controlPoint2);
        var results3 = scopeToResults.get(controlPoint3);
        assertTrue(results1.isPass());
        assertTrue(results2.isPass());
        assertTrue(results3.isPass());
    }


}