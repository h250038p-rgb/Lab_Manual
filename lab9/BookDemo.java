import java.util.*;

/**
 * Lab 9 - Exercise 3 (Intermediate)
 * Book with equals/hashCode by ISBN; prove a HashSet<Book> rejects duplicates.
 * EXPLAIN: HashSet uses hashCode() to find the bucket, then equals() to confirm a match.
 *          Override BOTH together, using the same fields, or the set will not detect duplicates.
 */
class Book {
    private final String isbn;
    private final String title;
    Book(String isbn, String title) { this.isbn = isbn; this.title = title; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;      // pattern matching
        return isbn.equals(other.isbn);                    // identity = ISBN only
    }
    @Override public int hashCode() { return Objects.hash(isbn); }   // consistent with equals
    @Override public String toString() { return title + " [" + isbn + "]"; }
}

public class BookDemo {
    public static void main(String[] args) {
        Set<Book> shelf = new HashSet<>();
        System.out.println("add 1st copy: " + shelf.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("add duplicate (same ISBN, different object): "
                + shelf.add(new Book("978-0132350884", "Clean Code (2nd copy)")));  // false -> rejected
        System.out.println("add different: " + shelf.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("Set size = " + shelf.size());    // 2
        System.out.println(shelf);
    }
}
