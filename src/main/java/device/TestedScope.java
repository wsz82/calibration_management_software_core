package device;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import scope.MultiScope;
import scope.Scope;
import unit.Parameter;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class TestedScope extends MultiScope {
    private final double accuracy;
    private final int resolutionExponent;

    public TestedScope(
            double accuracy,
            int resolutionExponent,
            Scope... scopes
    ) {
        super(scopes);
        this.accuracy = accuracy;
        this.resolutionExponent = resolutionExponent;
    }

    public TestedScope(double accuracy, int resolutionExponent, double minimumIncluded, double maximumExcluded) {
        this(accuracy, resolutionExponent, new Scope(new Parameter(minimumIncluded), new Parameter(maximumExcluded)));
    }

}