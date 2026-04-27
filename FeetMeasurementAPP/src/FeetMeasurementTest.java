import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    @Test
    void test_FeetToFeet_SameValue() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");
        FeetMeasurement f2 = new FeetMeasurement(1.0, "feet");

        assertTrue(f1.equals(f2));
    }

    @Test
    void test_InchToInch_SameValue() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, "inch");
        FeetMeasurement f2 = new FeetMeasurement(1.0, "inch");

        assertTrue(f1.equals(f2));
    }

    @Test
    void test_InchToFeet_EquivalentValue() {
        FeetMeasurement f1 = new FeetMeasurement(12.0, "inch");
        FeetMeasurement f2 = new FeetMeasurement(1.0, "feet");

        assertTrue(f1.equals(f2));
    }

    @Test
    void test_FeetToFeet_DifferentValue() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");
        FeetMeasurement f2 = new FeetMeasurement(2.0, "feet");

        assertFalse(f1.equals(f2));
    }

    @Test
    void test_ObjectEqualsItself() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");

        assertTrue(f1.equals(f1));
    }

    @Test
    void test_NullComparison() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, "feet");

        assertFalse(f1.equals(null));
    }

    @Test
    void test_InvalidUnit_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FeetMeasurement(1.0, "meter");
        });
    }
}