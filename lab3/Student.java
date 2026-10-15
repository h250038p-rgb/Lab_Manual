/**
 * Lab 3 - Exercises 1 and 2 (Basic)
 * Ex1: private name and marks (0-100); reject invalid marks in the setter.
 * Ex2: getGrade() derives A/B/C/F from marks WITHOUT storing it in a field.
 */
public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        setName(name);        // EXPLAIN: constructor reuses setters so validation lives in ONE place
        setMarks(marks);
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        this.name = name.trim();
    }

    // EXPLAIN: the invariant "0 <= marks <= 100" is enforced here, so no Student can break it
    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100, got " + marks);
        }
        this.marks = marks;
    }

    // EXPLAIN: grade is computed on demand, so it can never disagree with the marks
    public String getGrade() {
        if (marks >= 75) return "A";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        return "F";
    }

    public static void main(String[] args) {
        Student s = new Student("Tariro", 82);
        System.out.println(s.getName() + " -> " + s.getMarks() + " = " + s.getGrade());
        s.setMarks(55);
        System.out.println(s.getName() + " -> " + s.getMarks() + " = " + s.getGrade());
        try {
            s.setMarks(120);                      // invalid, rejected
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Marks unchanged: " + s.getMarks());
    }
}
