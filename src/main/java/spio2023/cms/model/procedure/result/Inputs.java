package spio2023.cms.model.procedure.result;

import lombok.Data;

import java.util.List;

@Data
public class Inputs {
    private final List<Double> referenceValues;
    private final List<Double> testValues;

}
