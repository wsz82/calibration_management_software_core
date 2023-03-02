package instrument;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import scope.MultiScope;
import scope.Scope;
import unit.Parameter;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ReferenceScope extends MultiScope {
    private final AccuracyPattern accuracyPattern;

    public ReferenceScope(AccuracyPattern accuracyPattern, Scope... scopes) {
        super(scopes);
        this.accuracyPattern = accuracyPattern;
    }

    public ReferenceScope(AccuracyPattern accuracyPattern, double minimumIncluded, double maximumExcluded) {
        this(accuracyPattern, new Scope(new Parameter(minimumIncluded), new Parameter(maximumExcluded)));
    }

}
