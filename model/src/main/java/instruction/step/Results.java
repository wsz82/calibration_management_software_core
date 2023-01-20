package instruction.step;

import unit.Unit;
import unit.Variable;

public record Results(Variable referencedValue, Variable checkedValue, Variable deviationPercentResult) {

    public Results(Unit unit) {
        this(new Variable(unit), new Variable(unit), new Variable(Unit.PERCENT));
    }


}
