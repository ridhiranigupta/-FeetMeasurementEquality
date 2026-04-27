import java.util.Objects;

public class FeetMeasurement {

    /**
     * =========================
     * UNIT ENUM (BASE = FEET)
     * =========================
     */
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
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
                case "yard":
                case "yards":
                    return YARD;
                case "cm":
                case "centimeter":
                case "centimeters":
                    return CM;
                default:
                    throw new IllegalArgumentException("Unsupported unit: " + unit);
            }
        }
    }

    private final double value;
    private final LengthUnit unit;

    public FeetMeasurement(double value, String unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = LengthUnit.fromString(unit);
    }

    private double toFeet() {
        return unit.toFeet(value);
    }

    /**
     * ==========================================
     * UC7 CORE: ADDITION WITH TARGET UNIT OUTPUT
     * ==========================================
     */
    public static FeetMeasurement add(FeetMeasurement a,
                                      FeetMeasurement b,
                                      LengthUnit targetUnit) {

        if (a == null || b == null || targetUnit == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        double sumInFeet = a.toFeet() + b.toFeet();

        double resultValue = targetUnit.fromFeet(sumInFeet);

        return new FeetMeasurement(resultValue, targetUnit.name());
    }

    /**
     * INSTANCE OVERLOAD (optional API style)
     */
    public FeetMeasurement add(FeetMeasurement other, LengthUnit targetUnit) {
        return add(this, other, targetUnit);
    }

    /**
     * EQUALITY (from UC3–UC6)
     */
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

    /**
     * DEMO
     */
    public static void main(String[] args) {

        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");
        FeetMeasurement f2 = new FeetMeasurement(12.0, "inch");

        System.out.println("Feet result: " +
                add(f1, f2, LengthUnit.FEET).value);

        System.out.println("Inch result: " +
                add(f1, f2, LengthUnit.INCH).value);

        System.out.println("Yard result: " +
                add(f1, f2, LengthUnit.YARD).value);
    }
}