/**
 * Lab 7 - Exercise 2 (Basic)
 * Method that accesses an array element and catches ArrayIndexOutOfBoundsException.
 */
public class ArrayAccess {
    static int getElement(int[] arr, int index) {
        try {
            return arr[index];            // valid indices are 0 .. length-1
        } catch (ArrayIndexOutOfBoundsException e) {
            // EXPLAIN: recover with a sensible default instead of crashing
            System.out.println("Index " + index + " is out of range (size " + arr.length + ")");
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        System.out.println(getElement(data, 1));   // 20
        System.out.println(getElement(data, 5));   // handled -> -1
    }
}
