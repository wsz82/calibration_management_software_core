package spio2023.calibrationmanagementsoftware.api.model.device;

import lombok.Data;
import spio2023.calibrationmanagementsoftware.api.model.scope.ScopeMatchable;
import spio2023.calibrationmanagementsoftware.api.model.unit.MeasurementType;

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