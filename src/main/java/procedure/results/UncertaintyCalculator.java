package procedure.results;

import unit.Prefix;

public interface UncertaintyCalculator {

    Results calculate(Prefix prefix, Inputs inputs);

}
