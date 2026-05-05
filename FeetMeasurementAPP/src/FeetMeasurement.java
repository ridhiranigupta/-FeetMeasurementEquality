import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

// ---------------- IMeasurable ----------------
interface IMeasurable {
    double toBase(double value);
    double fromBase(double baseValue);
}

// ---------------- LengthUnit ----------------
enum LengthUnit implements IMeasurable {
    FEET(v -> v * 12, v -> v / 12),
    INCHES(v -> v, v -> v);

    private final DoubleBinaryOperator toBase;
    private final DoubleBinaryOperator fromBase;

    LengthUnit(DoubleBinaryOperator toBase, DoubleBinaryOperator fromBase) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    public double toBase(double value) {
        return toBase.applyAsDouble(value, 1);
    }

    public double fromBase(double baseValue) {
        return fromBase.applyAsDouble(baseValue, 1);
    }
}

// ---------------- Quantity ----------------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    // ---------------- Arithmetic Enum ----------------
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        double apply(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    // ---------------- VALIDATION ----------------
    private void validate(Quantity<U> other, U targetUnit, boolean checkTarget) {
        if (other == null) throw new IllegalArgumentException("Other quantity is null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement types");

        if (Double.isNaN(other.value) || Double.isInfinite(other.value))
            throw new IllegalArgumentException("Invalid other value");

        if (checkTarget && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ---------------- CORE HELPER ----------------
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        double base1 = unit.toBase(value);
        double base2 = other.unit.toBase(other.value);
        return op.apply(base1, base2);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ---------------- ADD ----------------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(targetUnit.fromBase(base)), targetUnit);
    }

    // ---------------- SUBTRACT ----------------
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(targetUnit.fromBase(base)), targetUnit);
    }

    // ---------------- DIVIDE ----------------
    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

// ---------------- FeetMeasurement (MAIN DEMO CLASS) ----------------
public class FeetMeasurement {

    public static void main(String[] args) {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        // ADD
        System.out.println("ADD: " + q1.add(q2));

        // SUBTRACT
        System.out.println("SUBTRACT: " + q1.subtract(q2));

        // SUBTRACT explicit
        System.out.println("SUBTRACT (INCHES): " + q1.subtract(q2, LengthUnit.INCHES));

        // DIVIDE
        System.out.println("DIVIDE: " + q1.divide(new Quantity<>(2, LengthUnit.FEET)));

        // NEGATIVE
        System.out.println("NEGATIVE: " +
                new Quantity<>(5, LengthUnit.FEET)
                        .subtract(new Quantity<>(10, LengthUnit.FEET)));

        // ZERO
        System.out.println("ZERO: " +
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(120, LengthUnit.INCHES)));
    }
}