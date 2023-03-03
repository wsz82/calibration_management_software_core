package engine;

import instrument.ReferenceInstrument;
import procedure.Procedure;
import procedure.results.CalibrationOutput;

public interface CalibrationEngine {

    CalibrationOutput runCalibration(Procedure procedure, ReferenceInstrument instrument);

}
