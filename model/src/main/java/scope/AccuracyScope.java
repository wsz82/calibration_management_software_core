package scope;

public record AccuracyScope(double minimumIncluded,
                            double maximumExcluded,
                            double accuracy) implements Comparable<AccuracyScope> {

    @Override
    public int compareTo(AccuracyScope o) {
        return Double.compare(minimumIncluded, o.minimumIncluded);
    }


}