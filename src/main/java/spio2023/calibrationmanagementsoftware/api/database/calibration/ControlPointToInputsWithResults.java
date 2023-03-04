package spio2023.calibrationmanagementsoftware.api.database.calibration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.results.Inputs;
import spio2023.calibrationmanagementsoftware.api.database.results.Results;
import spio2023.calibrationmanagementsoftware.api.database.unit.ControlPoint;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class ControlPointToInputsWithResults extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Calibration calibration;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private ControlPoint controlPoint;

    @OneToOne(cascade = CascadeType.ALL)
    private Inputs inputs;

    @OneToOne(cascade = CascadeType.ALL)
    private Results results;

}
