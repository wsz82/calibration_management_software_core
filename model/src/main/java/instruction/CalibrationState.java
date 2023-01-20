package instruction;

import device.ReferenceDevice;
import instruction.step.Results;
import scope.Scope;
import scope.ScopesList;

import java.util.TreeMap;
import java.util.stream.Collectors;

public record CalibrationState(ReferenceDevice referenceDevice, TreeMap<Scope, Results> scopeToResults) {

    public CalibrationState(ReferenceDevice referenceDevice) {
        this(referenceDevice, makeScopeToResults(referenceDevice.scopes()));
    }

    private static TreeMap<Scope, Results> makeScopeToResults(ScopesList scopes) {
        var unit = scopes.scopeUnit();
        return scopes.list().stream()
                .collect(Collectors.toMap(scope -> scope, scope -> new Results(unit), (results1, results2) -> {
                    throw new IllegalStateException("Two equals scopes in the same list: " + scopes);
                }, TreeMap::new));
    }


}
