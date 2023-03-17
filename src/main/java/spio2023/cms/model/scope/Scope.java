package spio2023.cms.model.scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import spio2023.cms.model.unit.Parameter;
import spio2023.cms.model.unit.Prefix;

@Data
@AllArgsConstructor
public class Scope implements Comparable<Scope> {
    private final Parameter minimumIncluded;
    private final Parameter maximumExcluded;

    public Scope(Parameter parameter) {
        this(parameter, parameter);
    }

    public Scope(double value) {
        this(new Parameter(value), new Parameter(value));
    }

    public Scope(Prefix prefix, double value) {
        this(new Parameter(prefix, value), new Parameter(prefix, value));
    }

    public Scope(Prefix prefix1, double value1, Prefix prefix2, double value2) {
        this(new Parameter(prefix1, value1), new Parameter(prefix2, value2));
    }

    public Scope(double minimumIncluded, double maximumExcluded) {
        this(new Parameter(minimumIncluded), new Parameter(maximumExcluded));
    }

    public boolean isMatch(double value) {
        var minimum = minimumIncluded.normalizedValue();
        return value == minimum ||
                (value >= minimum
                        && value < maximumExcluded.normalizedValue());
    }

    @Override
    public int compareTo(Scope o) {
        if (o == null) {
            return 1;
        }
        return Double.compare(this.maximumExcluded.normalizedValue() - this.minimumIncluded.normalizedValue(),
                o.maximumExcluded.normalizedValue() - o.minimumIncluded.normalizedValue());
    }

}