/**
 * Lab 2 - Exercise 4 (Intermediate)
 * Two static blocks + two instance blocks.
 *
 * PREDICTION (before running):
 *   static block 1
 *   static block 2
 *   --- first object ---
 *   instance block 1
 *   instance block 2
 *   constructor
 *   --- second object ---
 *   instance block 1
 *   instance block 2
 *   constructor
 *
 * REASON: static blocks run ONCE when the class loads, in written order.
 *         Instance blocks run for EVERY object, in written order, before the constructor body.
 */
public class BlockOrder {
    static { System.out.println("static block 1"); }
    { System.out.println("instance block 1"); }
    static { System.out.println("static block 2"); }
    { System.out.println("instance block 2"); }

    BlockOrder() { System.out.println("constructor"); }

    public static void main(String[] args) {
        System.out.println("--- first object ---");
        new BlockOrder();
        System.out.println("--- second object ---");
        new BlockOrder();
    }
}
