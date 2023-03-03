package spio2023.calibrationmanagementsoftware.api.database.device;

import jakarta.persistence.*;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.procedure.ProcedureData;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class TestDevice extends BaseEntity {

    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private ProcedureData procedureData;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MeasurementTypeToTestScopes> measurementTypeToTestScopes;

    public TestDevice(device.TestDevice model, ProcedureData procedureData) {
        this.model = model.getModel();
        this.procedureData = procedureData;
        this.measurementTypeToTestScopes = model.getScopes().entrySet().stream()
                .map(MeasurementTypeToTestScopes::new)
                .collect(Collectors.toList());
    }

}
