package spio2023.calibrationmanagementsoftware.api.model.procedure;

import lombok.Data;

@Data
public class Settings {
    private final int measurementsNumber;
    private final boolean referenceValuesFromControlPoint;
}
