package spio2023.calibrationmanagementsoftware.api.database.instrument;

import jakarta.persistence.Entity;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class AccuracyPattern extends BaseEntity {

    private double part;

    private double constant;

    public AccuracyPattern(instrument.AccuracyPattern model) {
        this.part = model.getPart();
        this.constant = model.getConstant();
    }
}
