import java.util.*;

/**
 * Lab 8 - Exercises 1 and 2 (Basic)
 * Ex1: ArrayList of 10 ints -> sum, max, reverse print.
 * Ex2: remove duplicate names with a Set.
 */
public class ListOps {
    public static void main(String[] args) {
        // EXPLAIN: declare with the interface (List), construct with the implementation
        List<Integer> nums = new ArrayList<>(List.of(4, 8, 15, 16, 23, 42, 7, 3, 19, 11));

        int sum = 0;
        for (int n : nums) sum += n;                      // auto-unboxing Integer -> int
        int max = Collections.max(nums);
        System.out.println("List: " + nums);
        System.out.println("Sum = " + sum + ", Max = " + max);

        List<Integer> reversed = new ArrayList<>(nums);
        Collections.reverse(reversed);
        System.out.println("Reversed: " + reversed);

        // ---- Exercise 2 ----
        List<String> names = List.of("Tendai", "Rudo", "Tendai", "Farai", "Rudo", "Kuda");
        // EXPLAIN: a Set rejects duplicates; LinkedHashSet also keeps the original insertion order
        Set<String> unique = new LinkedHashSet<>(names);
        System.out.println("With duplicates : " + names);
        System.out.println("Without duplicates: " + unique);
    }
}
