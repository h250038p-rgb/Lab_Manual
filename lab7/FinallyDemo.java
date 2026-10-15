/**
 * Lab 7 - Exercise 5 (Challenge)
 * return inside try + print inside finally.
 *
 * PREDICTION: prints "finally runs" FIRST, then "result = 1".
 *
 * WHY finally STILL EXECUTES: the JVM guarantees the finally block runs whenever control leaves
 * the try block - by normal completion, an exception, or a return. The return value (1) is
 * computed and stored, then finally executes, then the method actually returns. (Only
 * System.exit() or a JVM crash prevents it.)
 * WARNING: a 'return' inside finally would OVERRIDE the try's return value - avoid that.
 */
public class FinallyDemo {
    static int test() {
        try {
            System.out.println("in try, about to return 1");
            return 1;
        } finally {
            System.out.println("finally runs");
        }
    }

    // Extra: shows that a return in finally overrides (bad practice)
    @SuppressWarnings("finally")
    static int bad() {
        try { return 1; } finally { return 2; }
    }

    public static void main(String[] args) {
        System.out.println("result = " + test());
        System.out.println("bad() = " + bad() + "  (finally's return wins - avoid!)");
    }
}
