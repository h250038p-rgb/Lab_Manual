/** A book: 14-day loan period. */
public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);                               // parent validates id and title
        if (author == null || author.isBlank() || author.contains(","))
            throw new IllegalArgumentException("Author required (no commas)");
        this.author = author.trim();
    }

    public String getAuthor() { return author; }
    @Override public int loanPeriodDays() { return 14; }
    @Override public String type()  { return "BOOK"; }
    @Override public String extra() { return author; }

    @Override public String toString() { return super.toString() + " by " + author; }
}
