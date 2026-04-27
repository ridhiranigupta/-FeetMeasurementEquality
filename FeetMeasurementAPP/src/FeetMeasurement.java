import java.util.Objects;

public class FeetMeasurement {

    // ENUM: Unit Abstraction (UC4 EXTENSION)
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.0328084); // 1 cm = 0.0328084 feet

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

    // CLASS FIELDS
    private final double value;
    private final LengthUnit unit;

    // CONSTRUCTOR
    public FeetMeasurement(double value, String unit) {
        this.value = value;
        this.unit = LengthUnit.fromString(unit);
    }

    // CONVERSION TO BASE UNIT (FEET)
    private double toFeet() {
        return unit.toFeet(value);
    }

    // EQUALS METHOD (UC4 CORE LOGIC)
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

    // MAIN METHOD (DEMO)
    public static void main(String[] args) {

        FeetMeasurement f1 = new FeetMeasurement(1.0, "yard");
        FeetMeasurement f2 = new FeetMeasurement(3.0, "feet");

        System.out.println("1 Yard = 3 Feet ? " + f1.equals(f2)); // true

        FeetMeasurement f3 = new FeetMeasurement(1.0, "yard");
        FeetMeasurement f4 = new FeetMeasurement(36.0, "inch");

        System.out.println("1 Yard = 36 Inch ? " + f3.equals(f4)); // true

        FeetMeasurement f5 = new FeetMeasurement(2.0, "cm");
        FeetMeasurement f6 = new FeetMeasurement(2.0, "cm");

        System.out.println("2 CM = 2 CM ? " + f5.equals(f6)); // true

        FeetMeasurement f7 = new FeetMeasurement(1.0, "cm");
        FeetMeasurement f8 = new FeetMeasurement(0.393701, "inch");

        System.out.println("1 CM = 0.393701 Inch ? " + f7.equals(f8)); // true

        FeetMeasurement f9 = new FeetMeasurement(2.0, "yard");
        FeetMeasurement f10 = new FeetMeasurement(6.0, "feet");

        System.out.println("2 Yard = 6 Feet ? " + f9.equals(f10)); // true
    }
}