package spio2023.calibrationmanagementsoftware.api.database.procedure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.calibration.Settings;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class ProcedureData extends BaseEntity {

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Settings settings;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProcedureStep> procedureSteps;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MeasurementTypeToControlPoints> measurementTypeToControlPoints;


    public ProcedureData(String name, procedure.Procedure input) {
        this.name = name;
        this.settings = new Settings(input.getSettings());
        this.procedureSteps = input.getSteps().stream()
                .map(ProcedureStep::new)
                .collect(Collectors.toList());
        this.measurementTypeToControlPoints = input.getControlPoints().entrySet().stream()
                .map(MeasurementTypeToControlPoints::new)
                .collect(Collectors.toList());
    }

}
