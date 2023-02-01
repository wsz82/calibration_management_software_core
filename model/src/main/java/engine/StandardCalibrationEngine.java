package engine;

import instruction.CalibrationState;
import instruction.Instruction;
import instruction.step.Results;
import instruction.step.Step;

import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StandardCalibrationEngine implements CalibrationEngine {
    private Consumer<String> messageConsumer;
    private Supplier<Double> inputSupplier;

    public StandardCalibrationEngine(Consumer<String> messageConsumer, Supplier<Double> inputSupplier) {
        this.messageConsumer = messageConsumer;
        this.inputSupplier = inputSupplier;
    }

    @Override
    public TreeMap<Double, Results> runCalibration(Instruction instruction) {
        var calibrationState = new CalibrationState(instruction.referenceDevice(), instruction.checkedDevice(),
                instruction.controlPoints());
        for (Step step : instruction.steps()) {
            step.setState(calibrationState);
            step.setMessageConsumer(messageConsumer);
            step.setInputSupplier(inputSupplier);
            step.show();
            step.execute();
        }
        return calibrationState.controlPointToResults();
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void setInputSupplier(Supplier<Double> inputSupplier) {
        this.inputSupplier = inputSupplier;
    }


}
