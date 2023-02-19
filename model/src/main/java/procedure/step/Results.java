package procedure.step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Results {

    private final List<Double> referencedValues;
    private final List<Double> checkedValues;
    private double meanReferencedValue;
    private double meanCheckedValue;
    private double error;
    private double uncertaintyA;
    private double uncertaintyB;
    private double uncertaintyC;
    private double uncertainty;
    private double lowerBoundary;
    private double upperBoundary;
    private boolean pass;

    public Results() {
        this.referencedValues = new ArrayList<>(1);
        this.checkedValues = new ArrayList<>(1);
    }

    public void addReferenceValue(double value) {
        referencedValues.add(value);
    }

    public void addCheckedValue(double value) {
        checkedValues.add(value);
    }

    public List<Double> getReadonlyReferencedValues() {
        return Collections.unmodifiableList(referencedValues);
    }

    public List<Double> getReadonlyCheckedValues() {
        return Collections.unmodifiableList(checkedValues);
    }

    public double getMeanReferencedValue() {
        return meanReferencedValue;
    }

    public void setMeanReferencedValue(double meanReferencedValue) {
        this.meanReferencedValue = meanReferencedValue;
    }

    public double getMeanCheckedValue() {
        return meanCheckedValue;
    }

    public void setMeanCheckedValue(double meanCheckedValue) {
        this.meanCheckedValue = meanCheckedValue;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getUncertaintyA() {
        return uncertaintyA;
    }

    public void setUncertaintyA(double uncertaintyA) {
        this.uncertaintyA = uncertaintyA;
    }

    public double getUncertaintyB() {
        return uncertaintyB;
    }

    public void setUncertaintyB(double uncertaintyB) {
        this.uncertaintyB = uncertaintyB;
    }

    public double getUncertaintyC() {
        return uncertaintyC;
    }

    public void setUncertaintyC(double uncertaintyC) {
        this.uncertaintyC = uncertaintyC;
    }

    public double getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(double uncertainty) {
        this.uncertainty = uncertainty;
    }

    public double getLowerBoundary() {
        return lowerBoundary;
    }

    public void setLowerBoundary(double lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
    }

    public double getUpperBoundary() {
        return upperBoundary;
    }

    public void setUpperBoundary(double upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Results{" +
                "referencedValues=" + referencedValues +
                ", checkedValues=" + checkedValues +
                ", meanReferencedValue=" + meanReferencedValue +
                ", meanCheckedValue=" + meanCheckedValue +
                ", error=" + error +
                ", uncertaintyA=" + uncertaintyA +
                ", uncertaintyB=" + uncertaintyB +
                ", uncertaintyC=" + uncertaintyC +
                ", uncertainty=" + uncertainty +
                ", lowerBoundary=" + lowerBoundary +
                ", upperBoundary=" + upperBoundary +
                ", pass=" + pass +
                '}';
    }


}
