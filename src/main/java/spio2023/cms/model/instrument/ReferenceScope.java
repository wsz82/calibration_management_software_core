package spio2023.cms.model.instrument;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spio2023.cms.model.scope.MultiScope;
import spio2023.cms.model.scope.Scope;
import spio2023.cms.model.unit.Parameter;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
