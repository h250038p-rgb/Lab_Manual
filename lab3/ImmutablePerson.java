import java.time.LocalDate;

/**
 * Lab 3 - Exercise 4 (Intermediate)
 * Immutable class: once created, it can never change.
 *
 * DESIGN CHOICES:
 *  1. 'final class'  -> nobody can subclass it and add mutable behaviour.
 *  2. 'private final' fields -> assigned once in the constructor, never reassigned.
 *  3. No setters -> no method can change state.
 *  4. Defensive copy of mutable inputs/outputs -> LocalDate is itself immutable, so safe to share;
 *     if we held a Date or array we would copy it in the constructor and in the getter.
 *  5. Validation in the constructor -> the object is valid for its entire life.
 * Benefits: thread-safe, safe as map keys, easy to reason about.
 */
public final class ImmutablePerson {
    private final String name;
    private final LocalDate dateOfBirth;

    public ImmutablePerson(String name, LocalDate dateOfBirth) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid date of birth");
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName()          { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    // EXPLAIN: "changing" a value returns a NEW object ("wither" pattern); the original is untouched
    public ImmutablePerson withName(String newName) {
        return new ImmutablePerson(newName, dateOfBirth);
    }

    @Override
    public String toString() { return name + " (born " + dateOfBirth + ")"; }

    public static void main(String[] args) {
        ImmutablePerson p1 = new ImmutablePerson("Tendai", LocalDate.of(2000, 5, 17));
        ImmutablePerson p2 = p1.withName("Tendai M.");
        System.out.println(p1);   // unchanged
        System.out.println(p2);   // new object
    }
}
