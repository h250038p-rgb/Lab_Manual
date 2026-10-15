/**
 * Lab 6 - Exercise 1 (Basic)
 * Abstract Shape with abstract area() and perimeter(); Circle, Rectangle, Square.
 * EXPLAIN: an abstract class cannot be instantiated; it forces subclasses to supply the
 *          missing methods, giving a common type with guaranteed behaviour.
 */
abstract class Shape {
    abstract double area();          // no body: WHAT, not HOW
    abstract double perimeter();
    // EXPLAIN: abstract classes may also hold concrete (shared) code
    void describe() {
        System.out.printf("%-10s area=%.2f perimeter=%.2f%n", getClass().getSimpleName(), area(), perimeter());
    }
}
class Circle extends Shape {
    private final double r;
    Circle(double r) { this.r = r; }
    double area()      { return Math.PI * r * r; }
    double perimeter() { return 2 * Math.PI * r; }
}
class Rectangle extends Shape {
    protected final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    double area()      { return w * h; }
    double perimeter() { return 2 * (w + h); }
}
class Square extends Rectangle {
    Square(double side) { super(side, side); }   // a square is a rectangle with equal sides
}

public class ShapeDemo {
    public static void main(String[] args) {
        // Shape s = new Shape();   // COMPILE ERROR: Shape is abstract
        Shape[] shapes = { new Circle(3), new Rectangle(4, 5), new Square(4) };
        for (Shape s : shapes) s.describe();
    }
}
