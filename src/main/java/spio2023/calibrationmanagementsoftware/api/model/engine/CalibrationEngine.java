package spio2023.calibrationmanagementsoftware.api.model.engine;

import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Procedure;
import spio2023.calibrationmanagementsoftware.api.model.procedure.results.CalibrationOutput;

public interface CalibrationEngine {

    CalibrationOutput runCalibration(Procedure procedure, ReferenceInstrument instrument);

}
