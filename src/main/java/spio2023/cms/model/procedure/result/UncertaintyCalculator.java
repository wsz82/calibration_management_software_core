package spio2023.cms.model.procedure.result;

import spio2023.cms.model.unit.Prefix;

public interface UncertaintyCalculator {

    Result calculate(Prefix prefix, Inputs inputs);

}
