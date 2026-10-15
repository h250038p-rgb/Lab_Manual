import java.util.*;
import java.util.stream.*;

/**
 * Lab 10 - Exercise 4 (Intermediate)
 * With a list of Student records, use streams to print names above the class average, sorted A-Z.
 */
record Student(String name, int marks) { }

public class AboveAverage {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Tendai", 72), new Student("Anesu", 85), new Student("Rudo", 60),
            new Student("Farai", 91), new Student("Kuda", 55), new Student("Chipo", 78));

        // EXPLAIN: mapToInt -> primitive stream; average() returns OptionalDouble
        double avg = students.stream().mapToInt(Student::marks).average().orElse(0);
        System.out.printf("Class average = %.2f%n", avg);

        // EXPLAIN: pipeline = source -> filter -> map -> sorted -> terminal operation
        List<String> above = students.stream()
                .filter(s -> s.marks() > avg)
                .map(Student::name)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Above average: " + above);
    }
}
