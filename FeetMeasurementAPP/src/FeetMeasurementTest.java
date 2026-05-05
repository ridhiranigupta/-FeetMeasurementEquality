import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetMeasurementTest {

    // -------- ADDITION (existing) --------
    @Test
    void testSubtract_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        assertEquals(5.0, q1.subtract(q2).getValue());
    }

    // -------- SUBTRACTION --------
    @Test
    void testSubtract_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(9.5, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtract_InchesTarget() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(114.0, q1.subtract(q2, LengthUnit.INCHES).getValue());
    }

    // -------- VOLUME TESTS (UC11) --------
    @Test
    void testSubtract_Negative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10, LengthUnit.FEET);

        assertEquals(-5.0, q1.subtract(q2).getValue());
    }

    @Test
    void testDivide_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivide_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }

    // -------- DIVISION --------
    @Test
    void testDivide_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testNullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    @Test
    void testImmutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10, q1.getValue());
    }
}