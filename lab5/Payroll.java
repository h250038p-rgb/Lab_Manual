/**
 * Lab 5 - Exercise 3 (Intermediate)
 * Employee hierarchy (Lab 4 style) extended with Intern.
 * The payroll loop never changes when a new subclass is added - that is polymorphism.
 */
class Employee {
    protected String name;
    protected double baseSalary;
    Employee(String name, double baseSalary) { this.name = name; this.baseSalary = baseSalary; }
    double calculatePay() { return baseSalary; }
}
class Manager extends Employee {
    private double bonus;
    Manager(String n, double s, double bonus) { super(n, s); this.bonus = bonus; }
    @Override double calculatePay() { return super.calculatePay() + bonus; }
}
class Developer extends Employee {
    private int overtimeHours;
    Developer(String n, double s, int ot) { super(n, s); this.overtimeHours = ot; }
    @Override double calculatePay() { return super.calculatePay() + overtimeHours * 20; }
}
// NEW class: added without touching the payroll loop
class Intern extends Employee {
    Intern(String n, double stipend) { super(n, stipend); }
    @Override double calculatePay() { return baseSalary * 0.5; }   // half-rate stipend
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] staff = {
            new Manager("Rudo", 5000, 1000),
            new Developer("Tendai", 4000, 10),
            new Intern("Kuda", 1200)
        };

        double total = 0;
        // EXPLAIN: this loop knows only 'Employee'. Each calculatePay() call is dispatched
        //          to the real object's version at run time.
        for (Employee e : staff) {
            double pay = e.calculatePay();
            System.out.printf("%-8s %s -> %.2f%n", e.name, e.getClass().getSimpleName(), pay);
            total += pay;
        }
        System.out.printf("Total payroll: %.2f%n", total);
    }
}
