import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetMeasurementTest {

    // ---------- FEET TESTS ----------
    @Test
    void testFeetEquality_SameValue() {
        assertTrue(FeetMeasurement.areFeetEqual(1.0, 1.0));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(FeetMeasurement.areFeetEqual(1.0, 2.0));
    }

    @Test
    void testFeetEquality_SameReference() {
        FeetMeasurement.Feet f = new FeetMeasurement.Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testFeetEquality_NullComparison() {
        FeetMeasurement.Feet f = new FeetMeasurement.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testFeetEquality_NonNumericInput() {
        FeetMeasurement.Feet f = new FeetMeasurement.Feet(1.0);
        assertFalse(f.equals("Invalid"));
    }

    // ---------- INCHES TESTS ----------
    @Test
    void testInchesEquality_SameValue() {
        assertTrue(FeetMeasurement.areInchesEqual(1.0, 1.0));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(FeetMeasurement.areInchesEqual(1.0, 2.0));
    }

    @Test
    void testInchesEquality_SameReference() {
        FeetMeasurement.Inches i = new FeetMeasurement.Inches(1.0);
        assertTrue(i.equals(i));
    }

    @Test
    void testInchesEquality_NullComparison() {
        FeetMeasurement.Inches i = new FeetMeasurement.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    void testInchesEquality_NonNumericInput() {
        FeetMeasurement.Inches i = new FeetMeasurement.Inches(1.0);
        assertFalse(i.equals(123)); // different type
    }
}