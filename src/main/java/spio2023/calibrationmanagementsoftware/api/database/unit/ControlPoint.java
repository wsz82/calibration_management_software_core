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
public class ControlPoint extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parameter> parameters;

    public ControlPoint(spio2023.calibrationmanagementsoftware.api.model.unit.ControlPoint model) {
        this.parameters = Arrays.stream(model.getParameters())
                .map(Parameter::new)
                .collect(Collectors.toList()
        );
    }

}
