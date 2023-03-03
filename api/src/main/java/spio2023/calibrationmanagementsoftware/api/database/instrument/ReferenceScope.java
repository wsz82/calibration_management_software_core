package spio2023.calibrationmanagementsoftware.api.database.instrument;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.unit.Scope;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class ReferenceScope extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Scope> scopes;

    @OneToOne(cascade = CascadeType.ALL)
    private AccuracyPattern accuracyPattern;

    public ReferenceScope(instrument.ReferenceScope model) {
        this.scopes = Arrays.stream(model.getScopes())
                .map(Scope::new)
                .collect(Collectors.toList());
        this.accuracyPattern = new AccuracyPattern(model.getAccuracyPattern());
    }

}
