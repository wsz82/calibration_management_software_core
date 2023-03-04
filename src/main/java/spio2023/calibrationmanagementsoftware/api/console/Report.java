package spio2023.calibrationmanagementsoftware.api.console;

import spio2023.calibrationmanagementsoftware.api.model.procedure.results.CalibrationOutput;
import spio2023.calibrationmanagementsoftware.api.model.procedure.results.Results;
import spio2023.calibrationmanagementsoftware.api.model.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.model.unit.MeasurementType;

import java.util.Map;

public class Report {
    private final CalibrationOutput calibrationOutput;

    public Report(CalibrationOutput calibrationOutput) {
        this.calibrationOutput = calibrationOutput;
    }

    public void print() {
        var results = calibrationOutput.getControlPointToResults();
        results.forEach(this::printMeasurement);
    }

    private void printMeasurement(MeasurementType measurementType, Map<ControlPoint, Results> controlPointResultsMap) {
        print("<--->");
        print(measurementType);
        controlPointResultsMap.forEach(this::printControlPointAndResults);
    }

    private void printControlPointAndResults(ControlPoint controlPoint, Results results) {
        print("---");
        print(controlPoint);
        print("---");
        var split = results.toString().split(", ");
        for (String resultItem : split) {
            print(resultItem);
        }
    }

    private void print(Object message) {
        System.out.println(message);
    }
}
