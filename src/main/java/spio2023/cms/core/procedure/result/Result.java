package spio2023.cms.core.procedure.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import spio2023.cms.core.unit.Prefix;

@Data
@AllArgsConstructor
public final class Result {
    private final Prefix prefix;
    private final Input input;
    private final double meanReferenceValue;
    private final double meanTestValue;
    private final double error;
    private final double uncertaintyA;
    private final double uncertaintyB;
    private final double uncertaintyC;
    private final double uncertainty;
    private final double lowerBoundary;
    private final double upperBoundary;
    private final boolean pass;
}
