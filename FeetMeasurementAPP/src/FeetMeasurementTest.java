import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // FEET ↔ INCHES
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

    // YARDS ↔ FEET
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

    // YARDS ↔ INCHES
    @Test
    void testConversion_YardsToInches() {
        double result = FeetMeasurement.convert(1.0,
                FeetMeasurement.LengthUnit.YARD,
                FeetMeasurement.LengthUnit.INCH);

        assertEquals(36.0, result, 0.0001);
    }

    // CM ↔ INCHES (approx)
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