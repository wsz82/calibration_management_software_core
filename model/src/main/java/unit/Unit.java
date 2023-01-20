package unit;

import java.util.Objects;

public record Unit(String name, String sign) {
    public static final Unit PERCENT = new Unit("Percent", "%");

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Unit) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
