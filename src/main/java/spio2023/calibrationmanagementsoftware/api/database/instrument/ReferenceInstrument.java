package spio2023.calibrationmanagementsoftware.api.database.instrument;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class ReferenceInstrument extends BaseEntity {

    private String model;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MeasurementTypeToReferenceScopes> measurementTypeToReferenceScopes;

    public ReferenceInstrument(spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument model) {
        this.model = model.getModel();
        this.measurementTypeToReferenceScopes = model.getScopes().entrySet().stream()
                .map(MeasurementTypeToReferenceScopes::new)
                .collect(Collectors.toList());
    }

}
