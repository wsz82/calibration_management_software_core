package instrument;

import scope.ScopeMatchable;
import unit.MeasurementType;

import java.util.List;
import java.util.Map;

public record ReferenceInstrument(
        String model,
        Map<MeasurementType, List<ReferenceScope>> scopes
) implements ScopeMatchable<ReferenceScope> {

    @Override
    public Map<MeasurementType, List<ReferenceScope>> measurementTypeToScopes() {
        return scopes;
    }

}