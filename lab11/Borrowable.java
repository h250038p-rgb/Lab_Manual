/**
 * Capability contract for anything that can be lent out.
 * EXPLAIN: an interface says WHAT can be done; LibraryItem decides HOW (abstraction).
 */
public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException;
    void giveBack();
}
