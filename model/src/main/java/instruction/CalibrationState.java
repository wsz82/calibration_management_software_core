package instruction;

import device.Device;
import instruction.step.Results;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public record CalibrationState(Device referenceDevice,
                               Device checkedDevice,
                               List<Double> controlPoints,
                               Settings settings,
                               TreeMap<Double, Results> controlPointToResults) {

    public CalibrationState(Device referenceDevice, Device checkedDevice, List<Double> controlPoints, Settings settings) {
        this(referenceDevice, checkedDevice, controlPoints, settings, makeControlPointToResults(controlPoints));
    }

    private static TreeMap<Double, Results> makeControlPointToResults(List<Double> controlPoints) {
        return controlPoints.stream()
                .collect(Collectors.toMap(
                        controlPoint -> controlPoint,
                        controlPoint -> new Results(),
                        (results1, results2) -> {
                            throw new IllegalStateException("Two equal control point in the same list: " + Arrays.toString(controlPoints.toArray()));
                        },
                        TreeMap::new));
    }


}
