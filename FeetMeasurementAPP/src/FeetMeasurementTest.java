import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // YARD EQUALITY TESTS (UC4)
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

    // YARDS ↔ INCHES
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

    // CENTIMETER TESTS (UC4)
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

    // COMPLEX EQUALITY SCENARIO
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        FeetMeasurement yard = new FeetMeasurement(2.0, "yard");   // 6 feet
        FeetMeasurement feet = new FeetMeasurement(6.0, "feet");
        FeetMeasurement inch = new FeetMeasurement(72.0, "inch");

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // FEET ↔ INCHES CONVERSION TESTS (UC5)
    @Test
    void testConversion_FeetToInches() {
        double result = FeetMeasurement.convert(1.0,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(12.0, result, 0.0001);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = FeetMeasurement.convert(24.0,
                FeetMeasurement.LengthUnit.INCH,
                FeetMeasurement.LengthUnit.FEET);

        assertEquals(2.0, result, 0.0001);
    }

    // YARDS ↔ FEET CONVERSION TESTS (UC5)
    @Test
    void testConversion_YardsToFeet() {
        double result = FeetMeasurement.convert(1.0,
                FeetMeasurement.LengthUnit.YARD,
                FeetMeasurement.LengthUnit.FEET);

        assertEquals(3.0, result, 0.0001);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = FeetMeasurement.convert(6.0,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.YARD);

        assertEquals(2.0, result, 0.0001);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = FeetMeasurement.convert(1.0,
                FeetMeasurement.LengthUnit.YARD,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(36.0, result, 0.0001);
    }

    // CM ↔ INCHES CONVERSION (approx)
    @Test
    void testConversion_CentimetersToInches() {
        double result = FeetMeasurement.convert(1.0,
                FeetMeasurement.LengthUnit.CM,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(0.393701, result, 0.0001);
    }

    // ZERO VALUE
    @Test
    void testConversion_ZeroValue() {
        double result = FeetMeasurement.convert(0.0,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(0.0, result, 0.0001);
    }

    // NEGATIVE VALUE
    @Test
    void testConversion_NegativeValue() {
        double result = FeetMeasurement.convert(-1.0,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(-12.0, result, 0.0001);
    }

    // SAME UNIT CONVERSION
    @Test
    void testConversion_SameUnit() {
        double result = FeetMeasurement.convert(5.0,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.FEET);

        assertEquals(5.0, result, 0.0001);
    }

    // ROUND TRIP TEST
    @Test
    void testConversion_RoundTrip() {
        double original = 10.0;

        double converted = FeetMeasurement.convert(original,
                FeetMeasurement.LengthUnit.YARD,
                FeetMeasurement.LengthUnit.FEET);

        double back = FeetMeasurement.convert(converted,
                FeetMeasurement.LengthUnit.FEET,
                FeetMeasurement.LengthUnit.YARD);

        assertEquals(original, back, 0.0001);
    }

    // INVALID UNIT TEST
    @Test
    void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            FeetMeasurement.convert(1.0, null,
                    FeetMeasurement.LengthUnit.FEET);
        });
    }

    // NAN / INVALID VALUE TEST
    @Test
    void testConversion_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            FeetMeasurement.convert(Double.NaN,
                    FeetMeasurement.LengthUnit.FEET,
                    FeetMeasurement.LengthUnit.INCH);
        });
    }

    // INSTANCE METHOD TEST
    @Test
    void testInstanceConversion() {
        FeetMeasurement obj = new FeetMeasurement(2.0, "yard");

        double result = obj.convertTo(FeetMeasurement.LengthUnit.FEET);

        assertEquals(6.0, result, 0.0001);
    }

    // COMPLEX CHAIN TEST
    @Test
    void testConversion_ComplexChain() {
        double inches = FeetMeasurement.convert(2.0,
                FeetMeasurement.LengthUnit.YARD,
                FeetMeasurement.LengthUnit.INCH);

        double feet = FeetMeasurement.convert(inches,
                FeetMeasurement.LengthUnit.INCH,
                FeetMeasurement.LengthUnit.FEET);

        assertEquals(6.0, feet, 0.0001);
    }
}
