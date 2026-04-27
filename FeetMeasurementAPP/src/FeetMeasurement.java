import java.util.Objects;

public class FeetMeasurement {

    /**
     * ENUM: UNIT SYSTEM (Immutable + Base = FEET)
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
     * =========================
     * UC6 CORE: ADDITION LOGIC
     * =========================
     */
    public static FeetMeasurement add(FeetMeasurement a, FeetMeasurement b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        double sumInFeet = a.toFeet() + b.toFeet();

        // result in unit of FIRST operand
        double resultValue = a.unit.fromFeet(sumInFeet);

        return new FeetMeasurement(resultValue, a.unit.name());
    }

    /**
     * INSTANCE VERSION (OO STYLE)
     */
    public FeetMeasurement add(FeetMeasurement other) {
        return add(this, other);
    }

    /**
     * EQUALITY (from UC3/UC4/UC5)
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
     * MAIN DEMO
     */
    public static void main(String[] args) {

        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");
        FeetMeasurement f2 = new FeetMeasurement(12.0, "inch");

        System.out.println("1 Feet + 12 Inch = " +
                FeetMeasurement.add(f1, f2).value + " " + f1.unit);

        FeetMeasurement y1 = new FeetMeasurement(1.0, "yard");
        FeetMeasurement f3 = new FeetMeasurement(3.0, "feet");

        System.out.println("1 Yard + 3 Feet = " +
                FeetMeasurement.add(y1, f3).value + " " + y1.unit);
    }
}