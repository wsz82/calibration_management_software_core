package spio2023.calibrationmanagementsoftware.api.database.procedure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;
import spio2023.calibrationmanagementsoftware.api.database.unit.ControlPoint;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.DisplayStep;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.InputsStep;
import spio2023.calibrationmanagementsoftware.api.model.procedure.step.Step;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class ProcedureStep extends BaseEntity {

    private String type;

    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    private ControlPoint controlPoint;

    public ProcedureStep(Step input) {
        this.type = input.getStepType().name();
        if (input instanceof DisplayStep concreteInput) {
            this.message = concreteInput.getMessage();
        } else if (input instanceof InputsStep concreteInput) {
            this.message = concreteInput.getMessage();
            this.controlPoint = new ControlPoint(concreteInput.getControlPoint());
        }
    }

}
