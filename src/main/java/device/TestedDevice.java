package device;

import scope.ScopeMatchable;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

public record TestedDevice(
        String model,
        Map<MeasurementType, List<TestedScope>> scopes
) implements ScopeMatchable<TestedScope> {

    @Override
    public Map<MeasurementType, List<TestedScope>> measurementTypeToScopes() {
        return scopes;
    }

}