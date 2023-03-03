package instrument;

import lombok.Data;
import scope.ScopeMatchable;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

@Data
public class ReferenceInstrument implements ScopeMatchable<ReferenceScope> {
    private final String model;
    private final Map<MeasurementType, List<ReferenceScope>> scopes;

    @Override
    public Map<MeasurementType, List<ReferenceScope>> measurementTypeToScopes() {
        return scopes;
    }

}