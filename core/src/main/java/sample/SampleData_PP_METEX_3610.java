package sample;

import device.TestDevice;
import device.TestScope;
import instrument.AccuracyPattern;
import instrument.ReferenceInstrument;
import instrument.ReferenceScope;
import procedure.Procedure;
import procedure.Settings;
import procedure.step.DisplayStep;
import procedure.step.InputsStep;
import scope.Scope;
import unit.ControlPoint;
import unit.MeasurementType;
import unit.Parameter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static unit.Prefix.*;

public class SampleData_PP_METEX_3610 {

    public static Procedure procedure() {
        var testedDevice = multimeter_PP_METEX_3610();
        var dcv = dcv();
        var acv = acv();
        var dca = dca();
        var aca = aca();
        var res = res();
        var controlPoints = Map.of(
                dcv, List.of(
                        new ControlPoint(MILLI, 180),
                        new ControlPoint(MILLI, -180),
                        new ControlPoint(MILLI, 20),
                        new ControlPoint(1.8),
                        new ControlPoint(-1.8),
                        new ControlPoint(0.2),
                        new ControlPoint(18),
                        new ControlPoint(-18),
                        new ControlPoint(10),
                        new ControlPoint(2),
                        new ControlPoint(-2),
                        new ControlPoint(180),
                        new ControlPoint(-180),
                        new ControlPoint(20),
                        new ControlPoint(900),
                        new ControlPoint(-900),
                        new ControlPoint(100)
                ),
                acv, List.of(
                        new ControlPoint(new Parameter(MILLI, 180), new Parameter(60)),
                        new ControlPoint(new Parameter(MILLI, 180), new Parameter(400)),
                        new ControlPoint(1.8, 60),
                        new ControlPoint(1.8, 400),
                        new ControlPoint(2, 60),
                        new ControlPoint(10, 60),
                        new ControlPoint(18, 60),
                        new ControlPoint(18, 400),
                        new ControlPoint(180, 60),
                        new ControlPoint(180, 400),
                        new ControlPoint(680, 60),
                        new ControlPoint(750, 60),
                        new ControlPoint(680, 400),
                        new ControlPoint(750, 400)
                ),
                dca, List.of(
                        new ControlPoint(MICRO, 180),
                        new ControlPoint(MILLI, 1.8),
                        new ControlPoint(MILLI, 18),
                        new ControlPoint(MILLI, 180),
                        new ControlPoint(1.8),
                        new ControlPoint(1),
                        new ControlPoint(18),
                        new ControlPoint(10)
                ),
                aca, List.of(
                        new ControlPoint(new Parameter(MICRO, 180), new Parameter(60)),
                        new ControlPoint(new Parameter(MICRO, 180), new Parameter(400)),
                        new ControlPoint(new Parameter(MILLI, 1.8), new Parameter(60)),
                        new ControlPoint(new Parameter(MILLI, 1.8), new Parameter(400)),
                        new ControlPoint(new Parameter(MILLI, 18), new Parameter(60)),
                        new ControlPoint(new Parameter(MILLI, 18), new Parameter(400)),
                        new ControlPoint(new Parameter(MILLI, 180), new Parameter(60)),
                        new ControlPoint(new Parameter(MILLI, 180), new Parameter(400)),
                        new ControlPoint(1.8, 60),
                        new ControlPoint(1.8, 400),
                        new ControlPoint(18, 60),
                        new ControlPoint(18, 400)
                ),
                res, List.of(
                        new ControlPoint(1),
                        new ControlPoint(190),
                        new ControlPoint(KILO, 19),
                        new ControlPoint(KILO, 190),
                        new ControlPoint(MEGA, 1.9),
                        new ControlPoint(MEGA, 19)
                )
        );
        var repeatingInstruction = "; Naciśnij przycisk OPERATE; Wprowadź wyniku pomiaru; Naciśnij przycisk STANDBY";
        var dcvPoints = controlPoints.get(dcv);
        var acvPoints = controlPoints.get(acv);
        var dcaPoints = controlPoints.get(dca);
        var acaPoints = controlPoints.get(aca);
        var resPoints = controlPoints.get(res);
        var settings = new Settings(1, true);
        return new Procedure(settings, testedDevice, controlPoints, Arrays.asList(
                new DisplayStep("Podczas kalibracji przyrządu jest używane NAPIĘCIE NIEBEZPIECZNE; Niestosowanie warunków bezpieczeństwa grozi śmiercią lub kalectwem"),
                new DisplayStep("Sprawdzenie przyrządu należy wykonać w warunkach:; Temperatura powietrza 23 stopni C +/-1 stop, C; Wilgotność względna powietrza do 60%"),
                new DisplayStep("Wyświetlacz sprawdzaj podczas kalibracji przyrządu"),
                new DisplayStep("Włóż baterie do miernika"),
                new DisplayStep("Włącz miernik - przycisk ON/OFF"),
                new DisplayStep("Podłącz przewody: Wyjście SENSE HI ------------>  V; Wyjście SENSE LO ------------>  COM"),
                new DisplayStep("Ustaw zakres 200mV napiecia stałego"),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 180mV" + repeatingInstruction,
                        dcv, dcvPoints.get(0)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -180mV" + repeatingInstruction,
                        dcv, dcvPoints.get(1)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 20mV" + repeatingInstruction,
                        dcv, dcvPoints.get(2)),
                new DisplayStep("Ustaw zakres 2V napiecia stałego"),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 1,8V" + repeatingInstruction,
                        dcv, dcvPoints.get(3)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -1,8V" + repeatingInstruction,
                        dcv, dcvPoints.get(4)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 0,2V" + repeatingInstruction,
                        dcv, dcvPoints.get(5)),
                new DisplayStep("Ustaw zakres 20V napiecia stałego"),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 18V" + repeatingInstruction,
                        dcv, dcvPoints.get(6)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -18V" + repeatingInstruction,
                        dcv, dcvPoints.get(7)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 10V" + repeatingInstruction,
                        dcv, dcvPoints.get(8)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 2V" + repeatingInstruction,
                        dcv, dcvPoints.get(9)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -2V" + repeatingInstruction,
                        dcv, dcvPoints.get(10)),
                new DisplayStep("Ustaw zakres 200V napiecia stałego"),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 180V" + repeatingInstruction,
                        dcv, dcvPoints.get(11)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -180V" + repeatingInstruction,
                        dcv, dcvPoints.get(12)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 20V" + repeatingInstruction,
                        dcv, dcvPoints.get(13)),
                new DisplayStep("Ustaw zakres 1000V napiecia stałego"),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 900V" + repeatingInstruction,
                        dcv, dcvPoints.get(14)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: -900V" + repeatingInstruction,
                        dcv, dcvPoints.get(15)),
                new InputsStep("Ustaw na kalibratorze napięcie stałe: 100V" + repeatingInstruction,
                        dcv, dcvPoints.get(16)),
                new DisplayStep("Ustaw zakres 200mV napiecia zmiennego"),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 180mV@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(0)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 180mV@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(1)),
                new DisplayStep("Ustaw zakres 2V napiecia zmiennego"),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 1,8V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(2)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 1,8V@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(3)),
                new DisplayStep("Ustaw zakres 20V napiecia zmiennego"),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 2V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(4)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 10V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(5)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 18V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(6)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne:18V@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(7)),
                new DisplayStep("Ustaw zakres 200V napiecia zmiennego"),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 180V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(8)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 180V@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(9)),
                new DisplayStep("Ustaw zakres 750V napiecia zmiennego"),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 680V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(10)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 750V@60Hz" + repeatingInstruction,
                        acv, acvPoints.get(11)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 680V@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(12)),
                new InputsStep("Ustaw na kalibratorze napięcie zmienne: 750V@400Hz" + repeatingInstruction,
                        acv, acvPoints.get(13)),
                new DisplayStep("Podłącz przewody: OUTPUT HI ------------>  A; OUTPUT LO ------------>  COM"),
                new DisplayStep("Ustaw zakres 200uA prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 180uA" + repeatingInstruction,
                        dca, dcaPoints.get(0)),
                new DisplayStep("Ustaw zakres 2mA prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 1,8mA" + repeatingInstruction,
                        dca, dcaPoints.get(1)),
                new DisplayStep("Ustaw zakres 20mA prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 18mA" + repeatingInstruction,
                        dca, dcaPoints.get(2)),
                new DisplayStep("Ustaw zakres 200mA prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 180mA" + repeatingInstruction,
                        dca, dcaPoints.get(3)),
                new DisplayStep("Ustaw zakres 2A prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 1,8A" + repeatingInstruction,
                        dca, dcaPoints.get(4)),
                new InputsStep("Ustaw na kalibratorze prąd stały: 1A" + repeatingInstruction,
                        dca, dcaPoints.get(5)),
                new DisplayStep("Zmień połączenie przewodów: 20A CURRENT OUTPUT HI   ------------>  20A; OUTPUT LO    ------------>  COM"),
                new DisplayStep("Ustaw zakres 20A prądu stałego"),
                new InputsStep("Ustaw na kalibratorze prąd stały: 18A" + repeatingInstruction,
                        dca, dcaPoints.get(6)),
                new InputsStep("Ustaw na kalibratorze prąd stały: 10A" + repeatingInstruction,
                        dca, dcaPoints.get(7)),
                new DisplayStep("Podłącz przewody:  OUTPUT HI ------------>  A; OUTPUT LO ------------>  COM"),
                new DisplayStep("Ustaw zakres 200uA prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 180uA@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(0)),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 180uA@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(1)),
                new DisplayStep("Ustaw zakres 2mA prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 1,8mA@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(2)),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 1,8mA@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(3)),
                new DisplayStep("Ustaw zakres 20mA prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 18mA@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(4)),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 18mA@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(5)),
                new DisplayStep("Ustaw zakres 200mA prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 180mA@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(6)),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 180mA@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(7)),
                new DisplayStep("Ustaw zakres 2A prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 1,8A@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(8)),
                new InputsStep("Ustaw na kalibratorze prąd zmienny: 1,8A@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(9)),
                new DisplayStep("Zmień połączenie przewodów: 20A CURRENT OUTPUT HI   ------------>  20A; OUTPUT LO    ------------>  COM"),
                new DisplayStep("Ustaw zakres 20A prądu zmiennego"),
                new InputsStep("Ustaw na kalibratorze prąd amienny: 18A@60Hz" + repeatingInstruction,
                        aca, acaPoints.get(10)),
                new InputsStep("Ustaw na kalibratorze prąd amienny: 18A@400Hz" + repeatingInstruction,
                        aca, acaPoints.get(11)),
                new DisplayStep("Zmień połączenie przewodów: SENSE HI ------------>  Ohm; SENSE LO ------------>  COM; OUTPUT HI ------------>  Ohm; OUTPUT LO ------------>  COM"),
                new DisplayStep("Ustaw zakres 200 Ohm"),
                new InputsStep("Ustaw na kalibratorze rezytancję: 1Ohm" + repeatingInstruction,
                        res, resPoints.get(0)),
                new InputsStep("Ustaw na kalibratorze rezytancję: 190Ohm" + repeatingInstruction,
                        res, resPoints.get(1)),
                new DisplayStep("Ustaw zakres 20 kOhm"),
                new InputsStep("Ustaw na kalibratorze rezytancję: 19kOhm" + repeatingInstruction,
                        res, resPoints.get(2)),
                new DisplayStep("Zmień połączenie przewodów: SENSE HI ------------>  Ohm; SENSE LO ------------>  COM"),
                new DisplayStep("Ustaw zakres 200 kOhm"),
                new InputsStep("Ustaw na kalibratorze rezytancję: 190kOhm" + repeatingInstruction,
                        res, resPoints.get(3)),
                new DisplayStep("Ustaw zakres 2 MOhm"),
                new InputsStep("Ustaw na kalibratorze rezytancję: 1,9MOhm" + repeatingInstruction,
                        res, resPoints.get(4)),
                new DisplayStep("Ustaw zakres 20 MOhm"),
                new InputsStep("Ustaw na kalibratorze rezytancję: 19MOhm" + repeatingInstruction,
                        res, resPoints.get(5)),
                new DisplayStep("P R O C E D U R A   K O M P L E T N A; Rozłącz wszystkie przewody; Wykonaj protokół kalibracji")
        ));
    }

    public static TestDevice multimeter_PP_METEX_3610() {
        var dcv = dcv();
        var acv = acv();
        var dca = dca();
        var aca = aca();
        var res = res();
        var fakeAccuracy = 1;
        //todo find accuracies for scopes
        var scopes = Map.of(
                dcv, List.of(
                        new TestScope(fakeAccuracy, -1, new Scope(MILLI, -180, MILLI, 181)),
                        new TestScope(fakeAccuracy, -3, new Scope(-1.8, 1.81)),
                        new TestScope(fakeAccuracy, -2, new Scope(-18, 18.1)),
                        new TestScope(fakeAccuracy, -1, new Scope(-180, 181)),
                        new TestScope(fakeAccuracy, 0, new Scope(-900, 901))
                ),
                acv, List.of(
                        new TestScope(fakeAccuracy, -1, new Scope(MILLI, 180), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -3, new Scope(1.8), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -2, new Scope(2, 18.1), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -1, new Scope(180), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, 0, new Scope(680, 751), new Scope(60, 401))
                ),
                dca, List.of(
                        new TestScope(fakeAccuracy, -1, new Scope(MICRO, 180)),
                        new TestScope(fakeAccuracy, -3, new Scope(MILLI, 1.8)),
                        new TestScope(fakeAccuracy, -2, new Scope(MILLI, 18)),
                        new TestScope(fakeAccuracy, -1, new Scope(MILLI, 180)),
                        new TestScope(fakeAccuracy, -3, new Scope(1, 1.81)),
                        new TestScope(fakeAccuracy, -2, new Scope(10, 18.1))
                ),
                aca, List.of(
                        new TestScope(fakeAccuracy, -1, new Scope(MICRO, 180), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -3, new Scope(MILLI, 1.8), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -2, new Scope(MILLI, 18), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -1, new Scope(MILLI, 180), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -3, new Scope(1.8), new Scope(60, 401)),
                        new TestScope(fakeAccuracy, -2, new Scope(18), new Scope(60, 401))
                ),
                res, List.of(
                        new TestScope(fakeAccuracy, -1, new Scope(1, 191)),
                        new TestScope(fakeAccuracy, -2, new Scope(KILO, 19)),
                        new TestScope(fakeAccuracy, -1, new Scope(KILO, 190)),
                        new TestScope(fakeAccuracy, -3, new Scope(MEGA, 1.9)),
                        new TestScope(fakeAccuracy, -2, new Scope(MEGA, 19))
                )
        );
        return new TestDevice("PP METEX 3610", scopes);
    }

    public static ReferenceInstrument multimeter_INMEL7000() {
        var dcv = dcv();
        var acv = acv();
        var dca = dca();
        var aca = aca();
        var res = res();
        var referenceScopes = Map.of(
                dcv, List.of(
                        new ReferenceScope(new AccuracyPattern(0.003, 0.000005), -0.21, 0.21),
                        new ReferenceScope(new AccuracyPattern(0.003, 0.0), -1050, 1050)
                ),
                acv, List.of(
                        new ReferenceScope(new AccuracyPattern(0.03, 0.00001), new Scope(0.00001, 0.21), new Scope(20, 20000)),
                        new ReferenceScope(new AccuracyPattern(0.02, 0.00002), new Scope(0.00001, 0.21), new Scope(20001, 100000)),
                        new ReferenceScope(new AccuracyPattern(0.03, 0.0), new Scope(0.1, 210), new Scope(20, 20000)),
                        new ReferenceScope(new AccuracyPattern(0.03, 0.0), new Scope(50.0, 1050), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.08, 0.0), new Scope(0.1, 210), new Scope(20001, 100000))
                ),
                dca, List.of(
                        new ReferenceScope(new AccuracyPattern(0.01, 0.0), -0.21, 0.21),
                        new ReferenceScope(new AccuracyPattern(0.02, 0.0), -2.1, 2.1),
                        new ReferenceScope(new AccuracyPattern(0.05, 0.0), -21, 21)
                ),
                aca, List.of(
                        new ReferenceScope(new AccuracyPattern(0.05, 0.0), new Scope(0.00001, 0.00021), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.04, 0.0), new Scope(0.0001, 0.0021), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.04, 0.0), new Scope(0.001, 0.021), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.04, 0.0), new Scope(0.01, 0.21), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.05, 0.0), new Scope(0.1, 2.1), new Scope(45, 5000)),
                        new ReferenceScope(new AccuracyPattern(0.08, 0.0), new Scope(1.0, 21.0), new Scope(45, 5000))
                ),
                res, List.of(
                        new ReferenceScope(new AccuracyPattern(0.0, 0.0001), 0, 0),
                        new ReferenceScope(new AccuracyPattern(0.015, 0.0), 1, 5),
                        new ReferenceScope(new AccuracyPattern(0.01, 0.0), 10, 1000000),
                        new ReferenceScope(new AccuracyPattern(0.03, 0.0), 1900000, 19000001)
                )

        );
        return new ReferenceInstrument("INMEL 7000", referenceScopes);
    }

    public static MeasurementType res() {
        return new MeasurementType("Rezystancja", "RES", "Ω");
    }

    public static MeasurementType aca() {
        return new MeasurementType("Prąd zmienny", "ACA", "A", "Hz");
    }

    public static MeasurementType dca() {
        return new MeasurementType("Prąd stały", "DCA", "A");
    }

    public static MeasurementType acv() {
        return new MeasurementType("Napięcie zmienne", "ACV", "V", "Hz");
    }

    public static MeasurementType dcv() {
        return new MeasurementType("Napięcie stałe", "DCV", "V");
    }

}
