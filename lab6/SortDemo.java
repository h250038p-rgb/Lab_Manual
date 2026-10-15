import java.util.*;

/**
 * Lab 6 - Exercise 4 (Intermediate)
 * Student implements Comparable<Student> (natural order = name) + Comparator by marks.
 */
class Student implements Comparable<Student> {
    private final String name;
    private final int marks;
    Student(String name, int marks) { this.name = name; this.marks = marks; }
    String getName() { return name; }
    int getMarks()   { return marks; }

    // EXPLAIN: natural ordering. Returns negative / 0 / positive like "this - other"
    @Override public int compareTo(Student other) { return this.name.compareTo(other.name); }

    @Override public String toString() { return name + "(" + marks + ")"; }
}

public class SortDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
            new Student("Tendai", 72), new Student("Anesu", 85),
            new Student("Rudo", 60), new Student("Farai", 91)));

        Collections.sort(list);                       // uses compareTo -> by name
        System.out.println("By name : " + list);

        // EXPLAIN: a Comparator is an EXTERNAL ordering, so we can have many without touching Student
        Comparator<Student> byMarks = Comparator.comparingInt(Student::getMarks);
        list.sort(byMarks);
        System.out.println("By marks: " + list);

        list.sort(byMarks.reversed());
        System.out.println("Top first: " + list);
    }
}
