package instruction.step;

import instruction.CalibrationState;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Step {
    protected CalibrationState state;
    protected Consumer<String> messageConsumer;
    protected Supplier<BigDecimal> inputSupplier;

    public Step() {
    }

    public abstract void show();

    public abstract void execute();

    public abstract StepType getStepType();

    public void setState(CalibrationState state) {
        this.state = state;
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void setInputSupplier(Supplier<BigDecimal> inputSupplier) {
        this.inputSupplier = inputSupplier;
    }
}
