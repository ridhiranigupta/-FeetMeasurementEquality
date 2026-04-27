import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // FEET target
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "feet"),
                new FeetMeasurement(12.0, "inch"),
                FeetMeasurement.LengthUnit.FEET);

        assertTrue(result.equals(new FeetMeasurement(2.0, "feet")));
    }

    // INCH target
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "feet"),
                new FeetMeasurement(12.0, "inch"),
                FeetMeasurement.LengthUnit.INCH);

        assertTrue(result.equals(new FeetMeasurement(24.0, "inch")));
    }

    // YARD target
    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "feet"),
                new FeetMeasurement(12.0, "inch"),
                FeetMeasurement.LengthUnit.YARD);

        assertTrue(result.equals(new FeetMeasurement(0.667, "yard")));
    }

    // CM target
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "inch"),
                new FeetMeasurement(1.0, "inch"),
                FeetMeasurement.LengthUnit.CM);

        assertTrue(result.equals(new FeetMeasurement(5.08, "cm")));
    }

    // SAME AS FIRST OPERAND
    @Test
    void testAddition_TargetSameAsFirstOperand() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(2.0, "yard"),
                new FeetMeasurement(3.0, "feet"),
                FeetMeasurement.LengthUnit.YARD);

        assertTrue(result.equals(new FeetMeasurement(3.0, "yard")));
    }

    // SAME AS SECOND OPERAND
    @Test
    void testAddition_TargetSameAsSecondOperand() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(2.0, "yard"),
                new FeetMeasurement(3.0, "feet"),
                FeetMeasurement.LengthUnit.FEET);

        assertTrue(result.equals(new FeetMeasurement(9.0, "feet")));
    }

    // COMMUTATIVITY
    @Test
    void testAddition_Commutativity() {
        FeetMeasurement a = new FeetMeasurement(1.0, "feet");
        FeetMeasurement b = new FeetMeasurement(12.0, "inch");

        FeetMeasurement r1 = FeetMeasurement.add(a, b, FeetMeasurement.LengthUnit.YARD);
        FeetMeasurement r2 = FeetMeasurement.add(b, a, FeetMeasurement.LengthUnit.YARD);

        assertTrue(r1.equals(r2));
    }

    // ZERO
    @Test
    void testAddition_WithZero() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(5.0, "feet"),
                new FeetMeasurement(0.0, "inch"),
                FeetMeasurement.LengthUnit.YARD);

        assertTrue(result.equals(new FeetMeasurement(1.667, "yard")));
    }

    // NEGATIVE
    @Test
    void testAddition_NegativeValues() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(5.0, "feet"),
                new FeetMeasurement(-2.0, "feet"),
                FeetMeasurement.LengthUnit.INCH);

        assertTrue(result.equals(new FeetMeasurement(36.0, "inch")));
    }

    // NULL TARGET UNIT
    @Test
    void testAddition_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            FeetMeasurement.add(
                    new FeetMeasurement(1.0, "feet"),
                    new FeetMeasurement(12.0, "inch"),
                    null);
        });
    }

    // LARGE VALUE
    @Test
    void testAddition_LargeValues() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1000.0, "feet"),
                new FeetMeasurement(500.0, "feet"),
                FeetMeasurement.LengthUnit.INCH);

        assertTrue(result.equals(new FeetMeasurement(18000.0, "inch")));
    }
}