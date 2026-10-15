/**
 * Lab 6 - Exercise 2 (Basic)
 * Interface Flyable implemented by Bird and Aeroplane, which share no superclass.
 * EXPLAIN: an interface describes a CAPABILITY; unrelated classes can promise it.
 */
interface Flyable {
    void fly();
}
class Bird implements Flyable {
    public void fly() { System.out.println("Bird flaps its wings and flies"); }
}
class Aeroplane implements Flyable {
    public void fly() { System.out.println("Aeroplane accelerates down the runway and takes off"); }
}

public class FlyableDemo {
    public static void main(String[] args) {
        // EXPLAIN: program to the interface - the loop does not care what the objects are
        Flyable[] things = { new Bird(), new Aeroplane() };
        for (Flyable f : things) f.fly();
    }
}
