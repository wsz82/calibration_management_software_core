package spio2023.calibrationmanagementsoftware.api.database.results;

import jakarta.persistence.Entity;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.model.unit.Prefix;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class Results extends BaseEntity {

    private Prefix prefix;

    private double meanReferenceValue;

    private double meanTestValue;

    private double error;

    private double uncertaintyA;

    private double uncertaintyB;

    private double uncertaintyC;

    private double uncertainty;

    private double lowerBoundary;

    private double upperBoundary;

    private boolean pass;

    public Results(spio2023.calibrationmanagementsoftware.api.model.procedure.results.Results model) {
        this.prefix = model.getPrefix();
        this.meanReferenceValue = model.getMeanReferenceValue();
        this.meanTestValue = model.getMeanTestValue();
        this.error = model.getError();
        this.uncertaintyA = model.getUncertaintyA();
        this.uncertaintyB = model.getUncertaintyB();
        this.uncertaintyC = model.getUncertaintyC();
        this.uncertainty = model.getUncertainty();
        this.lowerBoundary = model.getLowerBoundary();
        this.upperBoundary = model.getUpperBoundary();
        this.pass = model.isPass();
    }

}
