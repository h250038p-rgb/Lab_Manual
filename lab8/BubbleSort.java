import java.util.Arrays;

/**
 * Lab 8 - Exercise 5 (Challenge)
 * Generic <T extends Comparable<T>> bubbleSort.
 *
 * WHY int[] WILL NOT WORK: generics work only with reference types. T must be a class, and int
 * is a primitive; also int[] is not a T[] for any T (it is not an Object[]). Integer[] works
 * because Integer is a class (auto-boxing converts int <-> Integer for single values, but NOT
 * for whole arrays). Behind the scenes, type erasure replaces T by Comparable at run time, which
 * requires objects.
 */
public class BubbleSort {
    // EXPLAIN: the bound "extends Comparable<T>" guarantees compareTo() exists
    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {      // out of order -> swap
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;                              // already sorted: stop early
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 2, 9, 1, 7};
        String[] words = {"pear", "apple", "mango", "banana"};
        bubbleSort(nums);
        bubbleSort(words);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(words));

        int[] prim = {3, 1, 2};
        // bubbleSort(prim);   // COMPILE ERROR: int[] cannot be converted to T[] (T must be a reference type)
        System.out.println("int[] left alone: " + Arrays.toString(prim));
    }
}
