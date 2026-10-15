/**
 * Lab 1 - Exercise 5 (Challenge)
 * Person HAS-A Address. Chained dot expression, and what happens when address is null.
 */
class Address {
    String street;
    String city;
}

public class Person {
    String name;
    Address address;   // EXPLAIN: a field may itself be a reference to another object (may be null)

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "Tariro";
        p1.address = new Address();
        p1.address.street = "12 Main Road";
        p1.address.city = "Harare";

        // EXPLAIN: chained dot: person -> address -> city
        System.out.println(p1.name + " lives in " + p1.address.city);

        Person p2 = new Person();
        p2.name = "Farai";            // address was never assigned, so it is null

        // EXPLAIN: p2.address.city would throw NullPointerException because we would be
        //          asking "nothing" (null) for its city. Demonstrate it safely:
        try {
            System.out.println(p2.address.city);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: p2.address is null");
        }

        // EXPLAIN: the fix - check for null before following the reference
        if (p2.address != null) {
            System.out.println(p2.address.city);
        } else {
            System.out.println(p2.name + " has no address on record");
        }
    }
}
