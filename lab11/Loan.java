import java.time.LocalDate;

/** One borrowing event: which member holds which item, since when, and when it is due. */
public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowedOn;
    private final LocalDate dueDate;

    public Loan(LibraryItem item, Member member, LocalDate borrowedOn) {
        if (item == null || member == null || borrowedOn == null)
            throw new IllegalArgumentException("Loan needs an item, a member and a date");
        this.item = item;
        this.member = member;
        this.borrowedOn = borrowedOn;
        // EXPLAIN: the due date comes from the item's own loan period (polymorphic call)
        this.dueDate = borrowedOn.plusDays(item.loanPeriodDays());
    }

    public LibraryItem getItem()    { return item; }
    public Member getMember()       { return member; }
    public LocalDate getBorrowedOn(){ return borrowedOn; }
    public LocalDate getDueDate()   { return dueDate; }

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueDate); }

    @Override public String toString() {
        return item.getTitle() + " -> " + member.getName() + " (due " + dueDate + ")";
    }
}
