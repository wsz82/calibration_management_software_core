package spio2023.cms.model.procedure;

import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DefaultStepInterface implements StepInterface {
    private final Consumer<String> writer;
    private final Setting setting;
    private final Stack<Double> referenceInputs;
    private final Stack<Double> testInputs;

    public DefaultStepInterface(Consumer<String> writer, Setting setting, Stack<Double> referenceInputs, Stack<Double> testInputs) {
        this.writer = writer;
        this.setting = setting;
        this.referenceInputs = referenceInputs;
        this.testInputs = testInputs;
    }

    @Override
    public void showMessage(String message) {
        writer.accept(message);
    }

    @Override
    public Double[] getReferencedInput() {
        return provideInput(setting.getMeasurementSeries(), referenceInputs);
    }

    @Override
    public Double[] getCheckedInput() {
        return provideInput(setting.getMeasurementSeries(), testInputs);
    }

    private Double[] provideInput(int measurementsNumber, Stack<Double> source) {
        Double[] input = new Double[measurementsNumber];
        IntStream.range(0, measurementsNumber).forEach(i -> {
            input[i] = source.pop();
        });
        return input;
    }
}
