package spio2023.calibrationmanagementsoftware.api.model.procedure;

import java.util.Stack;
import java.util.stream.IntStream;

public class TestStepInterface implements StepInterface {
    private final Settings settings;
    private final Stack<Double> referenceInputs;
    private final Stack<Double> testInputs;

    public TestStepInterface(Settings settings, Stack<Double> referenceInputs, Stack<Double> testInputs) {
        this.settings = settings;
        this.referenceInputs = referenceInputs;
        this.testInputs = testInputs;
    }

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
}
