/**
 * Lab 1 - Exercise 4 (Intermediate)
 * Predict, then run: Rectangle r2 = r1; r2.width changed; print r1.width.
 *
 * PREDICTION: r1.width prints 99.0 (not 4.0).
 */
public class AliasDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(4, 5);

        // EXPLAIN: this copies the REFERENCE (the address), not the rectangle.
        //          r1 and r2 are two names for ONE object on the heap.
        Rectangle r2 = r1;

        r2.width = 99;   // modifies the single shared object

        System.out.println("r1.width = " + r1.width);   // 99.0
        System.out.println("r1 == r2 ? " + (r1 == r2)); // true -> same object

        /*
         * LAB RECORD EXPLANATION:
         * Object variables hold references. "Rectangle r2 = r1;" does not create a second
         * rectangle; it makes r2 point at the same one. A change made through r2 is therefore
         * visible through r1. To get an independent copy you would write
         * new Rectangle(r1.width, r1.height).
         */
        Rectangle r3 = new Rectangle(r1.width, r1.height);   // a true copy
        r3.width = 1;
        System.out.println("After editing the real copy r3, r1.width = " + r1.width); // still 99.0
    }
}
