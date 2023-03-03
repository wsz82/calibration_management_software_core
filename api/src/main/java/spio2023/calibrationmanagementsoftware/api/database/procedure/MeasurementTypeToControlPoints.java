package spio2023.calibrationmanagementsoftware.api.database.procedure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.database.unit.MeasurementType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class MeasurementTypeToControlPoints extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private MeasurementType measurementType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ControlPoint> controlPoints;

    public MeasurementTypeToControlPoints(Map.Entry<unit.MeasurementType, List<unit.ControlPoint>> entry) {
        this.measurementType = new MeasurementType(entry.getKey());
        this.controlPoints = entry.getValue().stream()
                .map(ControlPoint::new)
                .collect(Collectors.toList());
    }

}
