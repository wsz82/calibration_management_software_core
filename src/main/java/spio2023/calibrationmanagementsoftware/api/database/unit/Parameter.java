package spio2023.calibrationmanagementsoftware.api.database.unit;

import jakarta.persistence.Entity;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class Parameter extends BaseEntity {

    private String prefix;

    private double internalValue;

    public Parameter(spio2023.calibrationmanagementsoftware.api.model.unit.Parameter model) {
        this.prefix = model.getPrefix().name();
        this.internalValue = model.getValue();
    }

}
