/**
 * Lab 5 - Exercise 2 (Basic)
 * Animal with sound(); Cat, Cow, Duck; store in Animal[] and loop.
 */
class Animal {
    void sound() { System.out.println("Some generic animal sound"); }
}
class Cat extends Animal { @Override void sound() { System.out.println("Meow"); } }
class Cow extends Animal { @Override void sound() { System.out.println("Moo"); } }
class Duck extends Animal { @Override void sound() { System.out.println("Quack"); } }

public class AnimalDemo {
    public static void main(String[] args) {
        // EXPLAIN: array type is the parent; elements can be ANY subclass (upcasting is automatic)
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };

        for (Animal a : zoo) {
            // EXPLAIN: dynamic dispatch - the JVM looks at the REAL object at run time,
            //          so the right sound() runs even though 'a' is typed Animal
            a.sound();
        }
    }
}
