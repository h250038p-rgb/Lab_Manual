/**
 * Abstract parent of everything the library lends (inheritance + abstraction).
 * Encapsulation: all fields are private and validated.
 */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        // EXPLAIN: validate in the constructor so an invalid item can never exist.
        //          Commas are rejected because the CSV persistence format uses them as separators.
        if (id == null || id.isBlank() || id.contains(","))
            throw new IllegalArgumentException("Item id required (no commas)");
        if (title == null || title.isBlank() || title.contains(","))
            throw new IllegalArgumentException("Title required (no commas)");
        this.id = id.trim();
        this.title = title.trim();
    }

    // EXPLAIN: each subclass sets its own loan period -> polymorphism picks the right one at run time
    public abstract int loanPeriodDays();

    /** Short label used in the CSV file: BOOK or DVD. */
    public abstract String type();

    /** Extra, type-specific field saved in the CSV (author / minutes). */
    public abstract String extra();

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException("'" + title + "' is already on loan");
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean available) { this.available = available; }   // package-private: used when loading

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s, %d-day loan)", id, type(), title,
                available ? "available" : "on loan", loanPeriodDays());
    }
}
