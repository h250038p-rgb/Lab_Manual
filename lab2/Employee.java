/**
 * Lab 2 - Exercise 3 (Intermediate)
 * Employee with auto-incrementing id (static counter), three overloaded constructors
 * and a copy constructor.
 */
public class Employee {
    private static int nextId = 1000;       // EXPLAIN: shared counter, hands out unique ids

    private final int employeeId;           // final -> assigned exactly once per object
    private String name;
    private String department;
    private double salary;

    // Constructor 1: all details
    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;          // take the current id, then increment
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Constructor 2: chaining with defaults
    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    // Constructor 3: name only
    public Employee(String name) {
        this(name, "Unassigned");
    }

    // EXPLAIN: copy constructor - independent object with the same data.
    //          It still gets a NEW id because it is a different employee record.
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    @Override
    public String toString() {
        return "Employee#" + employeeId + " " + name + " | " + department + " | $" + salary;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Tendai", "IT", 2500);
        Employee e2 = new Employee("Rudo", "HR");
        Employee e3 = new Employee("Kuda");
        Employee e4 = new Employee(e1);       // copy of e1
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4 + "  (copy, new id)");
        System.out.println("e1 == e4 ? " + (e1 == e4));
    }
}
