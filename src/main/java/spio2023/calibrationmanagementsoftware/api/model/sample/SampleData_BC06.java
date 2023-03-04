package spio2023.calibrationmanagementsoftware.api.model.sample;

import spio2023.calibrationmanagementsoftware.api.model.device.TestDevice;
import spio2023.calibrationmanagementsoftware.api.model.device.TestScope;
import spio2023.calibrationmanagementsoftware.api.model.instrument.AccuracyPattern;
import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceScope;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Procedure;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Settings;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.DisplayStep;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.InputsStep;
import spio2023.calibrationmanagementsoftware.api.model.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.model.unit.MeasurementType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SampleData_BC06 {

    public static Procedure procedure() {
        var testedDevice = thermometer_BC06();
        var temperature = temperature();
        var controlPoints = Map.of(
                temperature, List.of(
                        new ControlPoint(-15),
                        new ControlPoint(20),
                        new ControlPoint(50)
                )
        );
        var settings = new Settings(5, false);
        var temperaturePoints = controlPoints.get(temperature);
        return new Procedure(settings, testedDevice, controlPoints, Arrays.asList(
                new DisplayStep("Kalibracja termometru rtęciowego"),
                new DisplayStep("Włącz komorę klimatyczną i ustaw temperaturę na -15"),
                new DisplayStep("Zmierz naprzemiennie temperaturę termometrem wzorcowym oraz kalibrowanym"),
                new InputsStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(0)),
                new DisplayStep("Ustaw temperaturę na 20"),
                new InputsStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(1)),
                new DisplayStep("Ustaw temperaturę na 50"),
                new InputsStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(2)),
                new DisplayStep("Kalibracja zakończona")
        ));
    }
    public static ReferenceInstrument thermometer_P755() {
        var temperature = temperature();
        var referenceScopes = Map.of(
                temperature, List.of(
                        new ReferenceScope(new AccuracyPattern(0.005, 4), -50, 200)
                )
        );
        return new ReferenceInstrument("P755", referenceScopes);
    }

    public static TestDevice thermometer_BC06() {
        var temperature = temperature();
        var resolutionExponent = -2;
        //todo spytać się Pawła czy te zakresy mają być zdefiniowane w testedDevice czy w InputsStep
        //na razie zostawię w testedDevice i wg mnie tak powinno byc
        var testedScopes = Map.of(
                temperature, List.of(
                        new TestScope(2, resolutionExponent, -20, 0),
                        new TestScope(1, resolutionExponent, 0, 40),
                        new TestScope(2, resolutionExponent, 40, 60)
                )
        );
        return new TestDevice("BC06", testedScopes);
    }

    public static MeasurementType temperature() {
        return new MeasurementType("Temperatura", "T", "°C");
    }

}
