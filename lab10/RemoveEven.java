import java.util.*;
import java.util.function.Predicate;

/**
 * Lab 10 - Exercise 2 (Basic)
 * Use a Predicate and removeIf to delete all even numbers from a list.
 * EXPLAIN: Predicate<T> is a functional interface (one method: boolean test(T)).
 *          A lambda  n -> n % 2 == 0  is a short way to implement it.
 */
public class RemoveEven {
    public static void main(String[] args) {
        // EXPLAIN: List.of is immutable, so wrap it in an ArrayList to allow removal
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Before: " + nums);

        Predicate<Integer> isEven = n -> n % 2 == 0;
        nums.removeIf(isEven);                       // removes every element for which test() is true

        System.out.println("After : " + nums);
    }
}
