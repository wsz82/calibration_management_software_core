package scope;

import java.math.BigDecimal;

public record Scope(BigDecimal minimumIncluded, BigDecimal maximumExcluded) implements Comparable<Scope> {

    @Override
    public int compareTo(Scope o) {
        return this.minimumIncluded.compareTo(o.minimumIncluded);
    }
}