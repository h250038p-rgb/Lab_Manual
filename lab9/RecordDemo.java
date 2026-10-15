/**
 * Lab 9 - Exercise 2 (Basic)
 * record Student(String name, int age): print, compare equal records, read a component.
 * EXPLAIN: a record is a compact immutable data class - the compiler generates the constructor,
 *          accessors (name(), age()), equals(), hashCode() and toString() for us.
 */
record Student(String name, int age) { }

public class RecordDemo {
    public static void main(String[] args) {
        Student a = new Student("Tariro", 20);
        Student b = new Student("Tariro", 20);
        Student c = new Student("Farai", 22);

        System.out.println(a);                          // Student[name=Tariro, age=20]
        System.out.println("a.equals(b) = " + a.equals(b));   // true: compared by VALUE
        System.out.println("a == b      = " + (a == b));      // false: different objects
        System.out.println("a.equals(c) = " + a.equals(c));
        System.out.println("Component: a.name() = " + a.name());   // accessor, not getName()
    }
}
