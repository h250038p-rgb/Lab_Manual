/**
 * Lab 4 - Exercises 1 and 2 (Basic)
 * Ex1: Person (name, age), Student extends Person (adds course); inherited display().
 * Ex2: Teacher subclass - shows Student and Teacher share the Person code.
 * File holds several classes; only People is public, so the file is People.java.
 */
class Person {
    protected String name;     // EXPLAIN: protected -> visible to subclasses
    protected int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // EXPLAIN: written ONCE here, inherited by every subclass (code reuse)
    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Student extends Person {            // Student IS-A Person
    private String course;

    Student(String name, int age, String course) {
        super(name, age);                 // EXPLAIN: parent part is built first via super(...)
        this.course = course;
    }

    String getCourse() { return course; }
}

class Teacher extends Person {            // Teacher IS-A Person
    private String subject;

    Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    String getSubject() { return subject; }
}

public class People {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 20, "Computer Science");
        Teacher t = new Teacher("Dr. Moyo", 45, "Mathematics");

        // EXPLAIN: neither subclass defines display(); both use Person's version
        s.display();
        System.out.println("  course: " + s.getCourse());
        t.display();
        System.out.println("  subject: " + t.getSubject());
    }
}
