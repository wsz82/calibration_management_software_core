package unit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Variable {
    private final Unit unit;
    private BigDecimal value = BigDecimal.ZERO;

    public Variable(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(4, RoundingMode.DOWN);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "unit=" + unit +
                ", value=" + value +
                '}';
    }
}
