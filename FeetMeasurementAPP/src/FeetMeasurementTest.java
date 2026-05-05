import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetMeasurementTest {

    // -------- ADDITION (existing) --------
    @Test
    void testAddition_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(2.0, q1.add(q2, LengthUnit.FEET).getValue());
    }

    // -------- SUBTRACTION --------
    @Test
    void testSubtraction_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(5.0, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        assertEquals(9.5, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtraction_ExplicitUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        assertEquals(114.0, q1.subtract(q2, LengthUnit.INCHES).getValue());
    }

    @Test
    void testSubtraction_NegativeResult() {
        Quantity<WeightUnit> q1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(-3.0, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtraction_ZeroResult() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(0.0, v1.subtract(v2).getValue());
    }

    // -------- DIVISION --------
    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivision_LessThanOne() {
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(0.5, v1.divide(v2));
    }

    @Test
    void testDivision_GreaterThanOne() {
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(2.0, w1.divide(w2));
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    // -------- CROSS CATEGORY --------
    @Test
    void testCrossCategory_Subtraction() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight));
    }

    @Test
    void testCrossCategory_Division() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }

    // -------- VALIDATION --------
    @Test
    void testNullSubtraction() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.subtract(null));
    }

    @Test
    void testNullDivision() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.divide(null));
    }

    // -------- IMMUTABILITY --------
    @Test
    void testImmutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10.0, q1.getValue()); // unchanged
    }
}