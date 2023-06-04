package spio2023.cms.core.sample;

import spio2023.cms.core.device.TestDevice;
import spio2023.cms.core.device.TestScope;
import spio2023.cms.core.instrument.AccuracyPattern;
import spio2023.cms.core.instrument.ReferenceInstrument;
import spio2023.cms.core.instrument.ReferenceScope;
import spio2023.cms.core.procedure.Procedure;
import spio2023.cms.core.procedure.Setting;
import spio2023.cms.core.procedure.step.DisplayStep;
import spio2023.cms.core.procedure.step.InputStep;
import spio2023.cms.core.scope.Scope;
import spio2023.cms.core.unit.ControlPoint;
import spio2023.cms.core.unit.MeasurementType;
import spio2023.cms.core.unit.Parameter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SampleData_BC06 {

    public static Procedure procedure() {
        var temperature = temperature();
        var controlPoints = Map.of(
                temperature, List.of(
                        new ControlPoint(-15),
                        new ControlPoint(20),
                        new ControlPoint(50)
                )
        );
        var settings = new Setting(5, false);
        var temperaturePoints = controlPoints.get(temperature);
        return new Procedure(settings, thermometer_BC06(), thermometer_P755(), controlPoints, Arrays.asList(
                new DisplayStep("Kalibracja termometru rtęciowego"),
                new DisplayStep("Włącz komorę klimatyczną i ustaw temperaturę na -15"),
                new DisplayStep("Zmierz naprzemiennie temperaturę termometrem wzorcowym oraz kalibrowanym"),
                new InputStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(0)),
                new DisplayStep("Ustaw temperaturę na 20"),
                new InputStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(1)),
                new DisplayStep("Ustaw temperaturę na 50"),
                new InputStep("Wprowadź pomiary: ", temperature, temperaturePoints.get(2)),
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
        var testedScopes = Map.of(
                temperature, List.of(
                        new TestScope(1.0, 0, -1, 0, new Scope(new Parameter(-20), new Parameter(0))),
                        new TestScope(1.0, 0, -1, 0, 0, 40),
                        new TestScope(1.0, 0, -1, 0, 40, 60)
                )
        );
        return new TestDevice("BC06", testedScopes);
    }

    public static MeasurementType temperature() {
        return new MeasurementType("Temperatura", "T", "°C");
    }

}
