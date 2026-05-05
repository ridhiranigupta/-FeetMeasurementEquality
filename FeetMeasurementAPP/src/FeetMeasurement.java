public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // -------- LENGTH EXAMPLES --------
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCH);

        // Subtraction
        Quantity<LengthUnit> sub1 = length1.subtract(length2);
        System.out.println("10 ft - 6 in = " + sub1);

        Quantity<LengthUnit> sub2 = length1.subtract(length2, LengthUnit.INCH);
        System.out.println("10 ft - 6 in (inches) = " + sub2);

        // Division
        double div1 = length1.divide(new Quantity<>(2.0, LengthUnit.FEET));
        System.out.println("10 ft / 2 ft = " + div1);

        double div2 = new Quantity<>(24.0, LengthUnit.INCH)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        System.out.println("24 in / 2 ft = " + div2);


        // -------- WEIGHT EXAMPLES --------
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("10 kg - 5000 g = " + w1.subtract(w2));
        System.out.println("10 kg / 5 kg = " +
                w1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));


        // -------- VOLUME EXAMPLES --------
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("5 L - 500 mL = " + v1.subtract(v2));
        System.out.println("5 L / 10 L = " +
                v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}