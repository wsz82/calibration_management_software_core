package spio2023.cms.core.engine;

import spio2023.cms.core.procedure.Procedure;
import spio2023.cms.core.procedure.result.CalibrationOutput;

public interface CalibrationEngine {

    CalibrationOutput runCalibration(Procedure procedure);

}
