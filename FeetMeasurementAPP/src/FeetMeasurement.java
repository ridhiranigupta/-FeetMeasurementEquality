public class FeetMeasurement {

    // =========================
    // LENGTH UNIT (UC8 REFACTORED ENUM)
    // =========================
    public enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        // Convert THIS unit → FEET (base unit)
        public double convertToBaseUnit(double value) {
            return value * toFeetFactor;
        }

        // Convert FEET → THIS unit
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toFeetFactor;
        }
    }

    // =========================
    // FIELDS
    // =========================
    private final double value;
    private final LengthUnit unit;

    public FeetMeasurement(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    // =========================
    // BASE CONVERSION (FEET)
    // =========================
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // =========================
    // UC8: CONVERT METHOD
    // =========================
    public FeetMeasurement convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new FeetMeasurement(converted, targetUnit);
    }

    // =========================
    // UC6 + UC7 + UC8: ADDITION
    // =========================
    public FeetMeasurement add(FeetMeasurement other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new FeetMeasurement(result, targetUnit);
    }

    // =========================
    // UC1–UC8: EQUALITY
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof FeetMeasurement)) return false;

        FeetMeasurement other = (FeetMeasurement) obj;

        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return "FeetMeasurement(" + value + ", " + unit + ")";
    }

    // =========================
    // MAIN METHOD (DEMO)
    // =========================
    public static void main(String[] args) {

        FeetMeasurement f1 = new FeetMeasurement(1.0, LengthUnit.FEET);
        FeetMeasurement f2 = new FeetMeasurement(12.0, LengthUnit.INCHES);

        System.out.println("Convert: " + f1.convertTo(LengthUnit.INCHES));
        System.out.println("Add (FEET): " + f1.add(f2, LengthUnit.FEET));
        System.out.println("Add (YARDS): " + f1.add(f2, LengthUnit.YARDS));
        System.out.println("Equals: " + f1.equals(f2));
    }
}