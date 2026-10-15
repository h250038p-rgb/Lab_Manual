/**
 * Lab 1 - Exercise 3 (Intermediate)
 * Rectangle with area() and perimeter(); array of 5 rectangles; print the largest by area.
 */
public class Rectangle {
    double width;
    double height;

    // EXPLAIN: a helper constructor keeps the demo short (Lab 2 covers constructors in depth)
    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double area()      { return width * height; }
    double perimeter() { return 2 * (width + height); }

    public static void main(String[] args) {
        // EXPLAIN: an array of objects holds REFERENCES; each slot starts as null until filled
        Rectangle[] rects = new Rectangle[5];
        rects[0] = new Rectangle(2, 3);
        rects[1] = new Rectangle(5, 4);
        rects[2] = new Rectangle(10, 1);
        rects[3] = new Rectangle(6, 6);
        rects[4] = new Rectangle(3, 3);

        // EXPLAIN: classic "find the maximum" - remember the best reference seen so far
        Rectangle largest = rects[0];
        for (Rectangle r : rects) {
            System.out.println(r.width + " x " + r.height + "  area=" + r.area()
                    + "  perimeter=" + r.perimeter());
            if (r.area() > largest.area()) {
                largest = r;
            }
        }
        System.out.println("Largest: " + largest.width + " x " + largest.height
                + " (area " + largest.area() + ")");
    }
}
