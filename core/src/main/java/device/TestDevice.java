package device;

import lombok.Data;
import scope.ScopeMatchable;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public class TestDevice implements ScopeMatchable<TestScope> {
    private final String model;
    private final Map<MeasurementType, List<TestScope>> scopes;

    @Override
    public Map<MeasurementType, List<TestScope>> measurementTypeToScopes() {
        return scopes;
    }

}