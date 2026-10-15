/**
 * Lab 7 - Exercise 3 (Intermediate)
 * InvalidAgeException (CHECKED) + validateAge(int) for values outside 0..120.
 */
// EXPLAIN: extending Exception (not RuntimeException) makes it CHECKED -> callers must handle/declare
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) { super(message); }
}

public class AgeCheck {
    // EXPLAIN: 'throws' advertises the checked exception in the method signature
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age " + age + " is outside the valid range 0-120");
        }
        System.out.println("Age " + age + " is valid");
    }

    public static void main(String[] args) {
        int[] ages = {25, -3, 130, 120};
        for (int age : ages) {
            try {
                validateAge(age);
            } catch (InvalidAgeException e) {      // compiler forces us to handle it
                System.out.println("Rejected: " + e.getMessage());
            }
        }
    }
}
