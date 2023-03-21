package spio2023.cms.core.procedure.result;

import spio2023.cms.core.unit.Prefix;

public interface UncertaintyCalculator {

    Result calculate(Prefix prefix, Input input);

}
