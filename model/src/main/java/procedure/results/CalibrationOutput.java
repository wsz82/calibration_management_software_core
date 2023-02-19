package procedure.results;

import java.util.Map;

public record CalibrationOutput(
        Map<Double, Results> controlPointToResults
) {

    public boolean isPass() {
        return controlPointToResults.values().stream().allMatch(Results::pass);
    }

}
