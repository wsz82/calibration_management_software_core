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
public class Unit extends BaseEntity {

    private String name;

    public Unit(String model) {
        this.name = model;
    }

}
