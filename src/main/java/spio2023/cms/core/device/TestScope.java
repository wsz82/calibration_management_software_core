package spio2023.cms.core.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spio2023.cms.core.scope.MultiScope;
import spio2023.cms.core.scope.Scope;
import spio2023.cms.core.unit.Parameter;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestScope extends MultiScope {
    private final double part;
    private final int resolutionExponent;
    private final int digits;
    private final Double overrideAccuracy;

    public TestScope(
            Double overrideAccuracy,
            double part,
            int resolutionExponent,
            int digits,
            Scope... scopes
    ) {
        super(scopes);
        this.overrideAccuracy = overrideAccuracy;
        this.part = part;
        this.resolutionExponent = resolutionExponent;
        this.digits = digits;
    }

    public TestScope(Double overrideAccuracy, double part, int resolutionExponent, int digits, double minimumIncluded, double maximumExcluded) {
        this(null, part, resolutionExponent, digits, new Scope(new Parameter(minimumIncluded), new Parameter(maximumExcluded)));
    }

}