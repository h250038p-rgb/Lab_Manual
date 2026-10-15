/**
 * Lab 9 - Exercise 1 (Basic)
 * enum Level { LOW, MEDIUM, HIGH } and a switch printing advice for each.
 * EXPLAIN: an enum is a fixed set of named constants; the compiler checks switch coverage.
 */
enum Level { LOW, MEDIUM, HIGH }

public class LevelAdvice {
    static String advice(Level level) {
        // EXPLAIN: modern switch expression - no fall-through, and must cover every constant
        return switch (level) {
            case LOW    -> "Relax - no action needed.";
            case MEDIUM -> "Keep an eye on it.";
            case HIGH   -> "Act immediately!";
        };
    }

    public static void main(String[] args) {
        for (Level l : Level.values()) {          // values() returns all constants in order
            System.out.println(l + " (ordinal " + l.ordinal() + "): " + advice(l));
        }
        System.out.println("Parsed: " + Level.valueOf("HIGH"));
    }
}
