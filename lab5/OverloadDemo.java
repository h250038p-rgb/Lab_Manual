import java.util.Arrays;

/**
 * Lab 5 - Exercise 1 (Basic)
 * Overload print() for int, double, String and int[].
 * EXPLAIN: overloading = same name, DIFFERENT parameter lists. The compiler picks the version
 *          at COMPILE time from the argument types (static / early binding).
 */
public class OverloadDemo {
    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String s)   { System.out.println("String: " + s); }
    static void print(int[] arr)  { System.out.println("int[]: " + Arrays.toString(arr)); }

    public static void main(String[] args) {
        print(42);                 // int version
        print(3.14);               // double version
        print("hello");            // String version
        print(new int[]{1, 2, 3}); // int[] version
        print('A');                // char widens to int -> int version (exact match absent)
    }
}
