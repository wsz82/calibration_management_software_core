package spio2023.calibrationmanagementsoftware.api.database.unit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class Scope extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Parameter minimumIncluded;

    @OneToOne(cascade = CascadeType.ALL)
    private Parameter maximumExcluded;

    public Scope(spio2023.calibrationmanagementsoftware.api.model.scope.Scope model) {
        this.minimumIncluded = new Parameter(model.getMinimumIncluded());
        this.maximumExcluded = new Parameter(model.getMaximumExcluded());
    }

}
