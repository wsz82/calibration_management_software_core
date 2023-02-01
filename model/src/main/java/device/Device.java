package device;

import scope.ScopesList;

public record Device(String model,
                     int resolutionExponent,
                     AccuracyPattern accuracyPattern,
                     ScopesList scopes) {

}