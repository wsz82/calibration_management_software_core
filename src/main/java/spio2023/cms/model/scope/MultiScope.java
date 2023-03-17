package spio2023.cms.model.scope;

import lombok.Data;
import spio2023.cms.model.unit.Parameter;

@Data
public class MultiScope implements Comparable<MultiScope> {
    private final Scope[] scopes;

    public MultiScope(Scope... scopes) {
        this.scopes = scopes;
    }

    public boolean isMatch(Parameter[] parameters) {
        var parametersLength = parameters.length;
        var scopesLength = scopes.length;
        if (parametersLength != scopesLength) {
            throw new IllegalStateException("Uneven number of parameters and scopes: " + "Parameters: " + parametersLength +
                    ", Scopes: " + scopesLength);
        }
        for (int i = 0; i < scopesLength; i++) {
            var parameter = parameters[i];
            var value = parameter.normalizedValue();
            var scope = scopes[i];
            var isMatch = scope.isMatch(value);
            if (!isMatch) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(MultiScope o) {
        if (o == null) {
            return 1;
        }
        var thisScopes = this.scopes;
        var thisLength = thisScopes.length;
        var otherScopes = o.scopes;
        var otherLength = otherScopes.length;
        for (int i = 0; i < thisLength; i++) {
            var scope = thisScopes[i];
            if (i < otherLength) {
                var otherScope = otherScopes[i];
                var comparison = scope.compareTo(otherScope);
                if (comparison != 0) {
                    return comparison;
                }
            }
        }
        return 0;
    }

}
