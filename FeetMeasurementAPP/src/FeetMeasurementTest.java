import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetMeasurementTest {

    // =========================
    // CONVERSION TESTS
    // =========================

    @Test
    void testConvert_FeetToInches() {
        FeetMeasurement f = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);

        FeetMeasurement result = f.convertTo(FeetMeasurement.LengthUnit.INCHES);

        assertEquals(new FeetMeasurement(12.0, FeetMeasurement.LengthUnit.INCHES), result);
    }

    @Test
    void testConvert_YardsToFeet() {
        FeetMeasurement f = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.YARDS);

        FeetMeasurement result = f.convertTo(FeetMeasurement.LengthUnit.FEET);

        assertEquals(new FeetMeasurement(3.0, FeetMeasurement.LengthUnit.FEET), result);
    }

    @Test
    void testConvert_CentimetersToInches() {
        FeetMeasurement f = new FeetMeasurement(2.54, FeetMeasurement.LengthUnit.CENTIMETERS);

        FeetMeasurement result = f.convertTo(FeetMeasurement.LengthUnit.INCHES);

        assertEquals(new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.INCHES), result);
    }

    // =========================
    // EQUALITY TESTS
    // =========================

    @Test
    void testEquality_FeetAndInches() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);
        FeetMeasurement f2 = new FeetMeasurement(12.0, FeetMeasurement.LengthUnit.INCHES);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_YardsAndFeet() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.YARDS);
        FeetMeasurement f2 = new FeetMeasurement(3.0, FeetMeasurement.LengthUnit.FEET);

        assertTrue(f1.equals(f2));
    }

    // =========================
    // ADDITION TESTS
    // =========================

    @Test
    void testAdd_SameUnit_Feet() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);
        FeetMeasurement f2 = new FeetMeasurement(2.0, FeetMeasurement.LengthUnit.FEET);

        FeetMeasurement result = f1.add(f2, FeetMeasurement.LengthUnit.FEET);

        assertEquals(new FeetMeasurement(3.0, FeetMeasurement.LengthUnit.FEET), result);
    }

    @Test
    void testAdd_CrossUnit_FeetAndInches() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);
        FeetMeasurement f2 = new FeetMeasurement(12.0, FeetMeasurement.LengthUnit.INCHES);

        FeetMeasurement result = f1.add(f2, FeetMeasurement.LengthUnit.FEET);

        assertEquals(new FeetMeasurement(2.0, FeetMeasurement.LengthUnit.FEET), result);
    }

    @Test
    void testAdd_YardsAndFeet() {
        FeetMeasurement f1 = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.YARDS);
        FeetMeasurement f2 = new FeetMeasurement(3.0, FeetMeasurement.LengthUnit.FEET);

        FeetMeasurement result = f1.add(f2, FeetMeasurement.LengthUnit.YARDS);

        assertEquals(new FeetMeasurement(2.0, FeetMeasurement.LengthUnit.YARDS), result);
    }

    // =========================
    // EDGE CASE TESTS
    // =========================

    @Test
    void testNullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new FeetMeasurement(1.0, null));
    }

    @Test
    void testNaNValueThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new FeetMeasurement(Double.NaN, FeetMeasurement.LengthUnit.FEET));
    }

    @Test
    void testNullAddThrowsException() {
        FeetMeasurement f = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> f.add(null, FeetMeasurement.LengthUnit.FEET));
    }

    @Test
    void testNullConvertThrowsException() {
        FeetMeasurement f = new FeetMeasurement(1.0, FeetMeasurement.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> f.convertTo(null));
    }
}