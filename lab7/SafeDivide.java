import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lab 7 - Exercise 1 (Basic)
 * Read two integers (Scanner) and divide, handling non-numeric input and division by zero.
 */
public class SafeDivide {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter numerator: ");
            int a = in.nextInt();           // throws InputMismatchException for "abc"
            System.out.print("Enter denominator: ");
            int b = in.nextInt();
            // EXPLAIN: integer division by zero throws ArithmeticException (unchecked)
            System.out.println(a + " / " + b + " = " + (a / b));
        } catch (InputMismatchException e) {
            System.out.println("Error: please enter whole numbers only.");
        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");
        } finally {
            // EXPLAIN: finally always runs - good place to release resources
            in.close();
            System.out.println("Done.");
        }
    }
}
