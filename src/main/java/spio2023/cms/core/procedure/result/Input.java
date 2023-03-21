package spio2023.cms.core.procedure.result;

import lombok.Data;

import java.util.List;

@Data
public class Input {
    private final List<Double> referenceValues;
    private final List<Double> testValues;

}
