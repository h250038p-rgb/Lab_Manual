import java.time.Year;

/**
 * Lab 1 - Exercises 1 and 2 (Basic)
 * Ex1: Car class with brand, year, mileage and display(); create three cars and print them.
 * Ex2: isAntique() returns true when the car is more than 25 years old.
 */
public class Car {
    // EXPLAIN: instance variables = the STATE of every Car object
    String brand;
    int year;
    double mileage;

    // EXPLAIN: behaviour - reads the object's own fields and prints them
    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km"
                + ", Antique? " + isAntique());
    }

    // EXPLAIN: Ex2 - age is computed from the current year, so the answer never goes stale
    boolean isAntique() {
        int age = Year.now().getValue() - year;
        return age > 25;               // "more than 25 years old"
    }

    public static void main(String[] args) {
        // EXPLAIN: each 'new' creates a separate object on the heap with its own field values
        Car c1 = new Car();
        c1.brand = "Toyota";  c1.year = 2018; c1.mileage = 85000.5;
        Car c2 = new Car();
        c2.brand = "Mercedes"; c2.year = 1985; c2.mileage = 210000;
        Car c3 = new Car();
        c3.brand = "Ford";    c3.year = 2005; c3.mileage = 150000;

        c1.display();
        c2.display();
        c3.display();
    }
}
