public class FeetMeasurement {

    // =========================
    // WEIGHT UNIT ENUM (UC9)
    // =========================
    public enum WeightUnit {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double toKgFactor;

        WeightUnit(double toKgFactor) {
            this.toKgFactor = toKgFactor;
        }

        // Convert this unit → KG (base unit)
        public double convertToBaseUnit(double value) {
            return value * toKgFactor;
        }

        // Convert KG → this unit
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toKgFactor;
        }
    }

    // =========================
    // FIELDS
    // =========================
    private final double value;
    private final WeightUnit unit;

    public FeetMeasurement(double value, WeightUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    // =========================
    // GETTERS
    // =========================
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // =========================
    // BASE CONVERSION (KG)
    // =========================
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // =========================
    // UC9: CONVERT
    // =========================
    public FeetMeasurement convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new FeetMeasurement(converted, targetUnit);
    }

    // =========================
    // UC9: ADD (DEFAULT UNIT = FIRST OPERAND)
    // =========================
    public FeetMeasurement add(FeetMeasurement other) {

        if (other == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumKg = this.toBaseUnit() + other.toBaseUnit();
        double result = this.unit.convertFromBaseUnit(sumKg);

        return new FeetMeasurement(result, this.unit);
    }

    // =========================
    // UC9: ADD (EXPLICIT TARGET UNIT)
    // =========================
    public FeetMeasurement add(FeetMeasurement other, WeightUnit targetUnit) {

        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumKg = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumKg);

        return new FeetMeasurement(result, targetUnit);
    }

    // =========================
    // EQUALITY (UC9 CROSS UNIT)
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

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

        FeetMeasurement kg =
                new FeetMeasurement(1.0, WeightUnit.KILOGRAM);

        FeetMeasurement gram =
                new FeetMeasurement(1000.0, WeightUnit.GRAM);

        FeetMeasurement pound =
                new FeetMeasurement(2.20462, WeightUnit.POUND);

        System.out.println("KG == GRAM: " + kg.equals(gram));

        System.out.println("Convert KG → GRAM: " +
                kg.convertTo(WeightUnit.GRAM));

        System.out.println("Add KG + GRAM (KG): " +
                kg.add(gram));

        System.out.println("Add KG + GRAM (GRAM): " +
                kg.add(gram, WeightUnit.GRAM));

        System.out.println("Add KG + POUND (KG): " +
                kg.add(pound, WeightUnit.KILOGRAM));
    }
}