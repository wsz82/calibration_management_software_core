package spio2023.calibrationmanagementsoftware.api.console;


import spio2023.calibrationmanagementsoftware.api.model.engine.DefaultCalibrationEngine;
import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Procedure;
import spio2023.calibrationmanagementsoftware.api.model.procedure.results.CalibrationOutput;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleApplication {
    private final Procedure procedure;
    private final ReferenceInstrument referenceInstrument;

    public ConsoleApplication(Procedure procedure, ReferenceInstrument referenceInstrument) {
        this.procedure = procedure;
        this.referenceInstrument = referenceInstrument;
    }

    public CalibrationOutput run() {
        var in = new Scanner(System.in);
        in.useLocale(Locale.US);
        var settings = procedure.getSettings();
        System.out.println(settings);
        var engine = new DefaultCalibrationEngine(new ConsoleStepInterface(in, settings));
        return engine.runCalibration(procedure, referenceInstrument);
    }

}
