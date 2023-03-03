package engine;

import org.junit.Test;
import procedure.Settings;
import procedure.StepInterface;
import procedure.results.CalibrationOutput;
import sample.SampleData_BC06;
import sample.SampleData_PP_METEX_3610;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class CalibrationEngineTest {

    @Test
    public void thermometer_calibration_BC06() {
        var procedure = SampleData_BC06.procedure();
        var referenceInputs = makeFakeInputs(
                -15.01, -15.00, -14.98, -14.99, -15.00,
                20.01, 20.02, 20.00, 19.99, 19.98,
                50.00, 50.01, 50.00, 49.99, 50.00
        );
        var testInputs = makeFakeInputs(
                -16.4, -16.1, -16.7, -16.4, -16.3,
                20.8, 20.6, 20.9, 20.4, 20.5,
                49.5, 48.5, 49.3, 48.1, 49.9
        );
        var settings = procedure.getSettings();
        var stepInterface = makeFakeStepInterface(settings, referenceInputs, testInputs);
        var referenceInstrument = SampleData_BC06.thermometer_P755();
        var engine = new DefaultCalibrationEngine(stepInterface);
        var output = engine.runCalibration(procedure, referenceInstrument);

        printOutput(output);
        assertTrue(output.isPass());
    }

    @Test
    public void multimeter_calibration_PP_METEX_3610_INMEL1000() {
        var procedure = SampleData_PP_METEX_3610.procedure();
        var testInputs = makeFakeInputs(
                180.01, -180.01, 20.01, 1.801, -1.801, 0.201, 18.01, -18.01, 10.01, 2.01, -2.01, 180.01, -180.01, 20.01, 900.01, -900.01, 100.01,
                180.02, 180.02, 1.802, 1.802, 2.02, 10.02, 18.02, 18.02, 180.02, 180.02, 680.02, 750.02, 680.02, 750.02,
                180.03, 1.803, 18.03, 180.03, 1.803, 1.03, 18.03, 10.03,
                180.04, 180.04, 1.804, 1.804, 18.04, 18.04, 180.04, 180.04, 1.804, 1.804, 18.04, 18.04,
                1.05, 190.05, 19.05, 190.05, 1.905, 19.05
        );
        var settings = procedure.getSettings();
        var stepInterface = makeFakeStepInterface(settings, null, testInputs);
        var engine = new DefaultCalibrationEngine(stepInterface);
        var output = engine.runCalibration(procedure, SampleData_PP_METEX_3610.multimeter_INMEL7000());

        printOutput(output);
        assertTrue(output.isPass());
    }

    private StepInterface makeFakeStepInterface(Settings settings, Stack<Double> referenceInputs, Stack<Double> testInputs) {
        return new StepInterface() {
            @Override
            public void showMessage(String message) {
                System.out.println(message);
            }

            @Override
            public Double[] getReferencedInput() {
                return provideInput(settings.getMeasurementsNumber(), referenceInputs);
            }

            @Override
            public Double[] getCheckedInput() {
                return provideInput(settings.getMeasurementsNumber(), testInputs);
            }

            private Double[] provideInput(int measurementsNumber, Stack<Double> source) {
                Double[] input = new Double[measurementsNumber];
                IntStream.range(0, measurementsNumber).forEach(i -> {
                    input[i] = source.pop();
                });
                return input;
            }
        };
    }

    private Stack<Double> makeFakeInputs(double... inputs) {
        List<Double> inputsList = DoubleStream.of(inputs)
                .boxed()
                .collect(Collectors.toList());
        Collections.reverse(inputsList);
        var nextInputs = new Stack<Double>();
        nextInputs.addAll(inputsList);
        return nextInputs;
    }

    private void printOutput(CalibrationOutput output) {
        var controlPointToResults = output.getControlPointToResults();
        controlPointToResults.forEach((controlPoint, results) -> {
            System.out.println("---");
            System.out.println(controlPoint);
            System.out.println(results);
        });
    }

}