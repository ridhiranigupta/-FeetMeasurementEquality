public class FeetMeasurement {

    // ---------- FEET CLASS ----------
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Feet)) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ---------- INCHES CLASS ----------
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Inches)) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ---------- STATIC METHODS ----------
    public static boolean areFeetEqual(double a, double b) {
        Feet f1 = new Feet(a);
        Feet f2 = new Feet(b);
        return f1.equals(f2);
    }

    public static boolean areInchesEqual(double a, double b) {
        Inches i1 = new Inches(a);
        Inches i2 = new Inches(b);
        return i1.equals(i2);
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {
        System.out.println("1.0 ft vs 1.0 ft: " + areFeetEqual(1.0, 1.0));
        System.out.println("1.0 inch vs 1.0 inch: " + areInchesEqual(1.0, 1.0));
    }
}