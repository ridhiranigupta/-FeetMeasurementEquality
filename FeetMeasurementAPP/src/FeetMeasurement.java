import java.util.Objects;

public class FeetMeasurement {

    /**
     * UNIT ENUM (Immutable + Conversion Factor based on FEET)
     */
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

    // CONSTRUCTOR
    public FeetMeasurement(double value, String unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = LengthUnit.fromString(unit);
    }

    /**
     * BASE CONVERSION (TO FEET)
     */
    private double toFeet() {
        return unit.toFeet(value);
    }

    /**
     * UC3/UC4: EQUALITY LOGIC
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
     * ============================
     * UC5 CORE FEATURE
     * UNIT-TO-UNIT CONVERSION API
     * ============================
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    /**
     * INSTANCE METHOD VERSION (OO STYLE)
     */
    public double convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double valueInFeet = this.toFeet();
        return targetUnit.fromFeet(valueInFeet);
    }

    /**
     * OVERLOADED DEMO METHOD 1
     */
    public static double demonstrateLengthConversion(
            double value, LengthUnit source, LengthUnit target) {

        return convert(value, source, target);
    }

    /**
     * OVERLOADED DEMO METHOD 2
     */
    public double demonstrateLengthConversion(LengthUnit target) {
        return convertTo(target);
    }

    /**
     * MAIN METHOD (TEST RUN)
     */
    public static void main(String[] args) {

        System.out.println("1 Feet -> Inches: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 Yards -> Feet: " +
                convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("36 Inches -> Yards: " +
                convert(36.0, LengthUnit.INCH, LengthUnit.YARD));

        System.out.println("1 CM -> Inches: " +
                convert(1.0, LengthUnit.CM, LengthUnit.INCH));

        FeetMeasurement obj = new FeetMeasurement(2.0, "yard");

        System.out.println("2 Yards -> Feet (instance): " +
                obj.demonstrateLengthConversion(LengthUnit.FEET));
    }
}
