package spio2023.calibrationmanagementsoftware.api.database.unit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class MeasurementType extends BaseEntity {

    private String name;

    private String symbol;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Unit> units;

    public MeasurementType(unit.MeasurementType model) {
        this.name = model.getName();
        this.symbol = model.getSymbol();
        this.units = Arrays.stream(model.getUnits())
                .map(Unit::new)
                .collect(Collectors.toList());
    }
}
