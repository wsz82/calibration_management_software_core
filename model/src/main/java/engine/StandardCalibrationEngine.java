package engine;

import instruction.CalibrationState;
import instruction.Instruction;
import instruction.step.Results;
import instruction.step.Step;
import scope.Scope;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StandardCalibrationEngine implements CalibrationEngine {
    private Consumer<String> messageConsumer;
    private Supplier<BigDecimal> inputSupplier;

    public StandardCalibrationEngine(Consumer<String> messageConsumer, Supplier<BigDecimal> inputSupplier) {
        this.messageConsumer = messageConsumer;
        this.inputSupplier = inputSupplier;
    }

    @Override
    public TreeMap<Scope, Results> runCalibration(Instruction instruction) {
        var calibrationState = new CalibrationState(instruction.referenceDevice());
        for (Step step : instruction.steps()) {
            step.setState(calibrationState);
            step.setMessageConsumer(messageConsumer);
            step.setInputSupplier(inputSupplier);
            step.show();
            step.execute();
        }
        return calibrationState.scopeToResults();
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void setInputSupplier(Supplier<BigDecimal> inputSupplier) {
        this.inputSupplier = inputSupplier;
    }


}
