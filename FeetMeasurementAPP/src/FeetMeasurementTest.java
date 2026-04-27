import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // SAME UNIT ADDITION
    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "feet"),
                new FeetMeasurement(2.0, "feet"));

        assertEquals(3.0, result.equals(
                new FeetMeasurement(3.0, "feet")), true);
    }

    // CROSS UNIT: FEET + INCH
    @Test
    void testAddition_FeetPlusInches() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "feet"),
                new FeetMeasurement(12.0, "inch"));

        assertTrue(result.equals(new FeetMeasurement(2.0, "feet")));
    }

    // CROSS UNIT: INCH + FEET
    @Test
    void testAddition_InchesPlusFeet() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(12.0, "inch"),
                new FeetMeasurement(1.0, "feet"));

        assertTrue(result.equals(new FeetMeasurement(24.0, "inch")));
    }

    // YARD + FEET
    @Test
    void testAddition_YardPlusFeet() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1.0, "yard"),
                new FeetMeasurement(3.0, "feet"));

        assertTrue(result.equals(new FeetMeasurement(2.0, "yard")));
    }

    // CM + INCH
    @Test
    void testAddition_CmPlusInch() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(2.54, "cm"),
                new FeetMeasurement(1.0, "inch"));

        assertTrue(result.equals(
                new FeetMeasurement(5.08, "cm")));
    }

    // COMMUTATIVITY
    @Test
    void testAddition_Commutativity() {
        FeetMeasurement a = new FeetMeasurement(1.0, "feet");
        FeetMeasurement b = new FeetMeasurement(12.0, "inch");

        FeetMeasurement r1 = FeetMeasurement.add(a, b);
        FeetMeasurement r2 = FeetMeasurement.add(b, a);

        assertTrue(r1.equals(r2));
    }

    // ZERO VALUE
    @Test
    void testAddition_WithZero() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(5.0, "feet"),
                new FeetMeasurement(0.0, "inch"));

        assertTrue(result.equals(new FeetMeasurement(5.0, "feet")));
    }

    // NEGATIVE VALUE
    @Test
    void testAddition_NegativeValues() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(5.0, "feet"),
                new FeetMeasurement(-2.0, "feet"));

        assertTrue(result.equals(new FeetMeasurement(3.0, "feet")));
    }

    // NULL CHECK
    @Test
    void testAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            FeetMeasurement.add(
                    new FeetMeasurement(1.0, "feet"),
                    null);
        });
    }

    // LARGE VALUE
    @Test
    void testAddition_LargeValues() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(1_000_000, "feet"),
                new FeetMeasurement(1_000_000, "feet"));

        assertTrue(result.equals(
                new FeetMeasurement(2_000_000, "feet")));
    }

    // SMALL VALUE
    @Test
    void testAddition_SmallValues() {
        FeetMeasurement result = FeetMeasurement.add(
                new FeetMeasurement(0.001, "feet"),
                new FeetMeasurement(0.002, "feet"));

        assertTrue(result.equals(
                new FeetMeasurement(0.003, "feet")));
    }
}