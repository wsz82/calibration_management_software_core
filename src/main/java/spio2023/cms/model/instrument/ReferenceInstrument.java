package spio2023.cms.model.instrument;

import lombok.Data;
import spio2023.cms.model.scope.ScopeMatchable;
import spio2023.cms.model.unit.MeasurementType;

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