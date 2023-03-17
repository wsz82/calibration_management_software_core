package spio2023.cms.model.scope;

import spio2023.cms.model.unit.ControlPoint;
import spio2023.cms.model.unit.MeasurementType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ScopeMatchable<M extends MultiScope> {

    //todo unit tests
    default M getMatchingScope(MeasurementType measurementType, ControlPoint controlPoint) {
        var accuracyScopes = measurementTypeToScopes().get(measurementType);
        if (accuracyScopes == null) {
            throw new IllegalStateException("No matching scope is defined for: " + measurementType);
        }
        var parameters = controlPoint.getParameters();
        var matchingScopes = accuracyScopes.stream()
                .filter(multiScope -> multiScope.isMatch(parameters))
                .collect(Collectors.toList());
        if (matchingScopes.isEmpty()) {
            throw new IllegalStateException("No matching scope is defined for: " + measurementType + ", " + controlPoint);
        }
        if (matchingScopes.size() > 1) {
            matchingScopes.sort(MultiScope::compareTo);
        }
        return matchingScopes.get(0);
    }

    Map<MeasurementType, List<M>> measurementTypeToScopes();


}
