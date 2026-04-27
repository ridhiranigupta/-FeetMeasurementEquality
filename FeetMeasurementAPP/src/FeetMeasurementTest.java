import org.junit.Test;
import static org.junit.Assert.*;

public class FeetMeasurementTest {

    /* ===================== LENGTH TESTS ===================== */

    @Test
    public void testFeetToInchesConversion() {
        FeetMeasurement.QuantityLength length =
                new FeetMeasurement.QuantityLength(1.0, FeetMeasurement.LengthUnit.FEET);

        FeetMeasurement.QuantityLength result = length.convertTo(FeetMeasurement.LengthUnit.INCHES);

        assertEquals(12.0, result.convertTo(FeetMeasurement.LengthUnit.INCHES).getBaseValue() * 12, 0.0001);
    }

    @Test
    public void testAddition_FeetAndInches() {
        FeetMeasurement.QuantityLength l1 =
                new FeetMeasurement.QuantityLength(1.0, FeetMeasurement.LengthUnit.FEET);
        FeetMeasurement.QuantityLength l2 =
                new FeetMeasurement.QuantityLength(12.0, FeetMeasurement.LengthUnit.INCHES);

        FeetMeasurement.QuantityLength result = l1.add(l2);

        assertTrue(result.equals(new FeetMeasurement.QuantityLength(2.0, FeetMeasurement.LengthUnit.FEET)));
    }

    @Test
    public void testEquality_Length() {
        FeetMeasurement.QuantityLength l1 =
                new FeetMeasurement.QuantityLength(1.0, FeetMeasurement.LengthUnit.FEET);
        FeetMeasurement.QuantityLength l2 =
                new FeetMeasurement.QuantityLength(12.0, FeetMeasurement.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    /* ===================== WEIGHT TESTS ===================== */

    @Test
    public void testEquality_kgAndGram() {
        FeetMeasurement.QuantityWeight w1 =
                new FeetMeasurement.QuantityWeight(1.0, FeetMeasurement.WeightUnit.KILOGRAM);
        FeetMeasurement.QuantityWeight w2 =
                new FeetMeasurement.QuantityWeight(1000.0, FeetMeasurement.WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    public void testConversion_PoundToKg() {
        FeetMeasurement.QuantityWeight w =
                new FeetMeasurement.QuantityWeight(2.20462, FeetMeasurement.WeightUnit.POUND);

        FeetMeasurement.QuantityWeight result = w.convertTo(FeetMeasurement.WeightUnit.KILOGRAM);

        assertEquals(1.0, result.convertTo(FeetMeasurement.WeightUnit.KILOGRAM).getBaseValue(), 0.01);
    }

    @Test
    public void testAddition_Weight() {
        FeetMeasurement.QuantityWeight w1 =
                new FeetMeasurement.QuantityWeight(1.0, FeetMeasurement.WeightUnit.KILOGRAM);
        FeetMeasurement.QuantityWeight w2 =
                new FeetMeasurement.QuantityWeight(1000.0, FeetMeasurement.WeightUnit.GRAM);

        FeetMeasurement.QuantityWeight result = w1.add(w2);

        assertTrue(result.equals(new FeetMeasurement.QuantityWeight(2.0, FeetMeasurement.WeightUnit.KILOGRAM)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        new FeetMeasurement.QuantityWeight(Double.NaN, FeetMeasurement.WeightUnit.KILOGRAM);
    }
}