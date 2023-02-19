package procedure;

import device.Device;
import procedure.results.Inputs;
import procedure.results.Results;

import java.util.*;

public record CalibrationState(Device referenceDevice,
                               Device checkedDevice,
                               List<Double> controlPoints,
                               Settings settings,
                               Map<Double, Inputs> controlPointToInputs,
                               Map<Double, Results> controlPointToResults) {

    public CalibrationState(Device referenceDevice, Device checkedDevice, List<Double> controlPoints, Settings settings) {
        this(referenceDevice, checkedDevice, controlPoints, settings,
                makeControlPointToInputs(controlPoints),
                makeControlPointToResults(controlPoints)
        );
    }

    private static Map<Double, Inputs> makeControlPointToInputs(List<Double> controlPoints) {
        var map = new HashMap<Double, Inputs>();
        controlPoints.forEach(val -> map.put(val, new Inputs(new ArrayList<>(), new ArrayList<>())));
        return map;
    }

    private static Map<Double, Results> makeControlPointToResults(List<Double> controlPoints) {
        var map = new TreeMap<Double, Results>();
        controlPoints.forEach(val -> map.put(val, null));
        return map;
    }


}
