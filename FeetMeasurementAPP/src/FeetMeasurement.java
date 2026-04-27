import java.util.Objects;

public class FeetMeasurement {

    // Enum for unit handling (DRY + type safety)
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public static LengthUnit fromString(String unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            switch (unit.toLowerCase()) {
                case "feet":
                    return FEET;
                case "inch":
                case "inches":
                    return INCH;
                default:
                    throw new IllegalArgumentException("Unsupported unit: " + unit);
            }
        }
    }

    // Quantity class logic
    private final double value;
    private final LengthUnit unit;

    public FeetMeasurement(double value, String unit) {
        this.value = value;
        this.unit = LengthUnit.fromString(unit);
    }

    private double toFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        FeetMeasurement other = (FeetMeasurement) obj;

        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toFeet());
    }

    // MAIN METHOD
    public static void main(String[] args) {

        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");
        FeetMeasurement f2 = new FeetMeasurement(12.0, "inch");

        System.out.println("1 Feet vs 12 Inch: " + f1.equals(f2)); // true

        FeetMeasurement f3 = new FeetMeasurement(1.0, "inch");
        FeetMeasurement f4 = new FeetMeasurement(1.0, "inch");

        System.out.println("1 Inch vs 1 Inch: " + f3.equals(f4)); // true

        FeetMeasurement f5 = new FeetMeasurement(2.0, "feet");

        System.out.println("1 Feet vs 2 Feet: " + f1.equals(f5)); // false
    }
}