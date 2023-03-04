package spio2023.calibrationmanagementsoftware.api.database.results;

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
public class Inputs extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<DoubleValue> referenceDoubleValues;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DoubleValue> testDoubleValues;

    public Inputs(spio2023.calibrationmanagementsoftware.api.model.procedure.results.Inputs model) {
        this.referenceDoubleValues = model.getReferenceValues().stream()
                .map(DoubleValue::new)
                .collect(Collectors.toList());
        this.testDoubleValues = model.getTestValues().stream()
                .map(DoubleValue::new)
                .collect(Collectors.toList());
    }

}
