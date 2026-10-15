/**
 * Lab 2 - Exercises 1 and 2 (Basic)
 * Ex1: constructor taking radius + no-arg constructor defaulting radius to 1.0
 * Ex2: static counter tracking how many circles exist, with a static getter
 */
public class Circle {
    // EXPLAIN: static -> ONE copy shared by every Circle, belongs to the class
    private static int count = 0;
    private double radius;           // instance field -> one per object

    public Circle(double radius) {
        this.radius = radius;        // this.radius = field, radius = parameter
        count++;                     // every construction bumps the shared counter
    }

    // EXPLAIN: constructor chaining - reuse the main constructor with the default value
    public Circle() {
        this(1.0);                   // must be the FIRST statement
    }

    public double area() { return Math.PI * radius * radius; }

    // EXPLAIN: static getter - callable as Circle.getCount() without any object
    public static int getCount() { return count; }

    public static void main(String[] args) {
        System.out.println("Circles at start: " + Circle.getCount());
        Circle a = new Circle(2.5);
        Circle b = new Circle();     // radius 1.0
        System.out.printf("a area = %.2f, b area = %.2f%n", a.area(), b.area());
        System.out.println("Circles created: " + Circle.getCount());   // 2
    }
}
