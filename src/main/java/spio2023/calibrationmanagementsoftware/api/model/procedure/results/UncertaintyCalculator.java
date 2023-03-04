package spio2023.calibrationmanagementsoftware.api.model.procedure.results;

import spio2023.calibrationmanagementsoftware.api.model.unit.Prefix;

public interface UncertaintyCalculator {

    Results calculate(Prefix prefix, Inputs inputs);

}
