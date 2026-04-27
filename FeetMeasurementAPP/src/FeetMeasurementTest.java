import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // YARD TESTS
    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(new FeetMeasurement(1.0, "yard")
                .equals(new FeetMeasurement(1.0, "yard")));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new FeetMeasurement(1.0, "yard")
                .equals(new FeetMeasurement(3.0, "feet")));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(new FeetMeasurement(3.0, "feet")
                .equals(new FeetMeasurement(1.0, "yard")));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new FeetMeasurement(1.0, "yard")
                .equals(new FeetMeasurement(36.0, "inch")));
    }

    @Test
    void testEquality_YardToFeet_DifferentValue() {
        assertFalse(new FeetMeasurement(1.0, "yard")
                .equals(new FeetMeasurement(2.0, "feet")));
    }

    // CENTIMETER TESTS
    @Test
    void testEquality_CmToCm_SameValue() {
        assertTrue(new FeetMeasurement(1.0, "cm")
                .equals(new FeetMeasurement(1.0, "cm")));
    }

    @Test
    void testEquality_CmToInch_EquivalentValue() {
        assertTrue(new FeetMeasurement(1.0, "cm")
                .equals(new FeetMeasurement(0.393701, "inch")));
    }

    @Test
    void testEquality_CmToFeet_NonEquivalentValue() {
        assertFalse(new FeetMeasurement(1.0, "cm")
                .equals(new FeetMeasurement(1.0, "feet")));
    }

    // MULTI-UNIT TRANSITIVE TEST
    @Test
    void testEquality_TransitiveProperty() {
        FeetMeasurement yard = new FeetMeasurement(1.0, "yard");
        FeetMeasurement feet = new FeetMeasurement(3.0, "feet");
        FeetMeasurement inch = new FeetMeasurement(36.0, "inch");

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // SAME REFERENCE
    @Test
    void testEquality_SameReference_Yard() {
        FeetMeasurement f = new FeetMeasurement(1.0, "yard");
        assertTrue(f.equals(f));
    }

    @Test
    void testEquality_SameReference_Cm() {
        FeetMeasurement f = new FeetMeasurement(1.0, "cm");
        assertTrue(f.equals(f));
    }

    // NULL TEST
    @Test
    void testEquality_NullComparison() {
        FeetMeasurement f = new FeetMeasurement(1.0, "feet");
        assertFalse(f.equals(null));
    }

    // INVALID UNIT TEST
    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FeetMeasurement(1.0, "meter");
        });
    }

    // COMPLEX SCENARIO
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        FeetMeasurement yard = new FeetMeasurement(2.0, "yard");   // 6 feet
        FeetMeasurement feet = new FeetMeasurement(6.0, "feet");
        FeetMeasurement inch = new FeetMeasurement(72.0, "inch");

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}