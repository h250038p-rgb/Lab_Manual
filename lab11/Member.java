import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A library member who may hold at most MAX_LOANS items at once. */
public class Member {
    public static final int MAX_LOANS = 3;       // EXPLAIN: constant = static final, UPPER_SNAKE_CASE

    private final String id;
    private final String name;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank() || id.contains(","))
            throw new IllegalArgumentException("Member id required (no commas)");
        if (name == null || name.isBlank() || name.contains(","))
            throw new IllegalArgumentException("Member name required (no commas)");
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId()   { return id; }
    public String getName() { return name; }

    // EXPLAIN: defensive copy - callers cannot modify our private list
    public List<Loan> getLoans() { return Collections.unmodifiableList(new ArrayList<>(loans)); }

    void addLoan(Loan loan) throws MemberLimitExceededException {
        if (loans.size() >= MAX_LOANS) {
            throw new MemberLimitExceededException(name + " already has " + MAX_LOANS + " loans");
        }
        loans.add(loan);
    }

    void removeLoan(Loan loan) { loans.remove(loan); }

    @Override public String toString() {
        return "[" + id + "] " + name + " (" + loans.size() + "/" + MAX_LOANS + " loans)";
    }
}
