/**
 * Lab 5 - Exercise 4 (Intermediate)
 * Output differs between a METHOD and a FIELD accessed through a superclass reference.
 *
 * EXPLANATION:
 *  - Methods are resolved at RUN time from the actual object  -> child's version runs.
 *  - Fields (and static methods) are resolved at COMPILE time from the reference's declared type
 *    -> the parent's field is read. Fields are "hidden", never overridden.
 */
class Parent {
    String label = "Parent field";
    String who()  { return "Parent method"; }
    static String kind() { return "Parent static"; }
}
class Child extends Parent {
    String label = "Child field";            // hides, does not override
    @Override String who() { return "Child method"; }
    static String kind() { return "Child static"; }   // hides, does not override
}

public class FieldVsMethod {
    public static void main(String[] args) {
        Parent ref = new Child();     // declared type Parent, real object Child

        System.out.println(ref.who());     // Child method  (dynamic dispatch)
        System.out.println(ref.label);     // Parent field  (decided by declared type)
        System.out.println(Parent.kind()); // Parent static (static: no polymorphism)

        Child real = (Child) ref;
        System.out.println(real.label);    // Child field   (declared type is now Child)
    }
}
