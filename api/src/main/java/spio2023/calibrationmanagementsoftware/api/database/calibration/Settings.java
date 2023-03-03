package spio2023.calibrationmanagementsoftware.api.database.calibration;

import jakarta.persistence.Entity;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class Settings extends BaseEntity {

    private int measurementsNumber;

    private boolean referenceValuesFromControlPoint;

    public Settings(procedure.Settings model) {
        this.measurementsNumber = model.getMeasurementsNumber();
        this.referenceValuesFromControlPoint = model.isReferenceValuesFromControlPoint();
    }

}
