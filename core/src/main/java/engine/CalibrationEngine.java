package engine;

import procedure.Procedure;
import procedure.Settings;
import procedure.results.CalibrationOutput;

public interface CalibrationEngine {

    CalibrationOutput runCalibration(Procedure procedure, Settings settings);



}
