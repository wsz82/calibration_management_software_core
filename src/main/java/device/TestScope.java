package device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import scope.MultiScope;
import scope.Scope;
import unit.Parameter;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestScope extends MultiScope {
    private final double accuracy;
    private final int resolutionExponent;

    public TestScope(
            double accuracy,
            int resolutionExponent,
            Scope... scopes
    ) {
        super(scopes);
        this.accuracy = accuracy;
        this.resolutionExponent = resolutionExponent;
    }

    public TestScope(double accuracy, int resolutionExponent, double minimumIncluded, double maximumExcluded) {
        this(accuracy, resolutionExponent, new Scope(new Parameter(minimumIncluded), new Parameter(maximumExcluded)));
    }

}