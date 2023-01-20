package device;

import scope.ScopesList;

import java.math.BigDecimal;

public record ReferenceDevice(Device device, BigDecimal tolerance, ScopesList scopes) {

}