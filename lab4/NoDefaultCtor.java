/**
 * Lab 4 - Exercise 4 (Intermediate)
 * Why does "class Dog extends Animal" fail when Animal only has Animal(String name)?
 *
 * EXPLANATION:
 * Every constructor must begin by calling a superclass constructor. If you do not write
 * super(...), the compiler inserts super() - the NO-ARGUMENT constructor. Because Animal
 * defines a constructor of its own, Java no longer supplies the default no-arg one, so the
 * hidden super() call has nothing to match and compilation fails with:
 *   "constructor Animal in class Animal cannot be applied to given types"
 *
 * BROKEN VERSION (kept as a comment so the file still compiles):
 *
 *   class Animal { Animal(String name) { ... } }
 *   class Dog extends Animal { }                  // ERROR: implicit super() does not exist
 *   class Dog extends Animal { Dog() { } }        // ERROR: same reason
 *
 * FIX 1 (used below): call the existing constructor explicitly: super(name).
 * FIX 2: also add a no-arg constructor to Animal.
 */
class Animal {
    protected final String name;
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor: " + name);
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);                 // EXPLAIN: explicit call satisfies the parent's only constructor
        System.out.println("Dog constructor");
    }
}

public class NoDefaultCtor {
    public static void main(String[] args) {
        Dog d = new Dog("Rex");
        System.out.println("Created dog named " + d.name);
    }
}
