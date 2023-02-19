package engine;

import procedure.Procedure;
import procedure.Settings;
import procedure.step.Results;

import java.util.TreeMap;

public interface CalibrationEngine {

    TreeMap<Double, Results> runCalibration(Procedure procedure, Settings settings);

}
