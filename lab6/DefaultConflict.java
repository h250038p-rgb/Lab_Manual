/**
 * Lab 6 - Exercise 5 (Challenge)
 * Two interfaces declare the same default method hello(); resolve with InterfaceName.super.hello().
 *
 * THE RULE: if a class inherits two default methods with the same signature, the compiler cannot
 * choose, so the class MUST override the method. Inside the override it may delegate to a
 * specific parent with  InterfaceName.super.method().  (A method declared in the class or a
 * superclass always wins over interface defaults - "class wins" rule.)
 */
interface English {
    default void hello() { System.out.println("Hello!"); }
}
interface Shona {
    default void hello() { System.out.println("Mhoro!"); }
}

class Greeter implements English, Shona {
    // Without this override: "types English and Shona are incompatible; both define hello()"
    @Override
    public void hello() {
        English.super.hello();     // explicitly call English's version
        Shona.super.hello();       // and Shona's version
        System.out.println("(Greeter resolved the conflict)");
    }
}

public class DefaultConflict {
    public static void main(String[] args) {
        new Greeter().hello();
    }
}
