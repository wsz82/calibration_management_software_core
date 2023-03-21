package spio2023.cms.core.procedure;

import lombok.Data;

@Data
public class Setting {
    private final int measurementSeries;
    private final boolean referenceValuesFromControlPoint;
}
