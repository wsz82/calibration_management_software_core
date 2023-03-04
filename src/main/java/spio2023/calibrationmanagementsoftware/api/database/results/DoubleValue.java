package spio2023.calibrationmanagementsoftware.api.database.results;

import jakarta.persistence.Entity;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class DoubleValue extends BaseEntity {

    private double internalValue;

    public DoubleValue(double model) {
        this.internalValue = model;
    }

}
