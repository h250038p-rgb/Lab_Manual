/**
 * Lab 4 - Exercise 3 (Intermediate)
 * Multilevel inheritance: Vehicle -> Car -> ElectricCar. Trace constructor call order.
 *
 * RULE: creating a subclass object runs constructors from the TOP of the hierarchy DOWN:
 *   Vehicle -> Car -> ElectricCar. Each constructor's first statement (explicit or implicit)
 *   is a call to super(...), so the parent finishes before the child body starts.
 */
class Vehicle {
    Vehicle() {
        System.out.println("1. Vehicle constructor");
    }
}

class Car extends Vehicle {
    Car() {
        super();   // implicit if omitted
        System.out.println("2. Car constructor");
    }
}

class ElectricCar extends Car {
    ElectricCar() {
        super();
        System.out.println("3. ElectricCar constructor");
    }
}

public class Vehicles {
    public static void main(String[] args) {
        System.out.println("Creating ElectricCar...");
        new ElectricCar();
    }
}
