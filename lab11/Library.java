import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Core service. Items and members live in Maps keyed by id; loans are held by the members.
 * Persistence: three CSV files (items.csv, members.csv, loans.csv).
 */
public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();

    // EXPLAIN: a day offset lets the demo "move time forward" to test overdue logic
    private int dayOffset = 0;
    public LocalDate today()            { return LocalDate.now().plusDays(dayOffset); }
    public void advanceDays(int days)   { dayOffset += days; }

    // ---------- adding ----------
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId()))
            throw new IllegalArgumentException("Item id already exists: " + item.getId());
        items.put(item.getId(), item);
    }

    public void registerMember(Member m) {
        if (members.containsKey(m.getId()))
            throw new IllegalArgumentException("Member id already exists: " + m.getId());
        members.put(m.getId(), m);
    }

    // ---------- borrow / return ----------
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item
        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);

        // 2. check the member's limit FIRST so a refused borrow never changes the item's state
        if (member.getLoans().size() >= Member.MAX_LOANS) {
            throw new MemberLimitExceededException(
                    member.getName() + " already has " + Member.MAX_LOANS + " loans");
        }
        item.borrow(member);                 // throws ItemNotAvailableException if on loan

        // 3. create the Loan and record it on the member
        Loan loan = new Loan(item, member, today());
        member.addLoan(loan);
        return loan;
    }

    public void giveBack(String itemId) {
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);
        Loan found = null;
        for (Member m : members.values())
            for (Loan l : m.getLoans())
                if (l.getItem() == item) { found = l; m.removeLoan(l); }
        if (found == null) throw new IllegalStateException("'" + item.getTitle() + "' is not on loan");
        item.giveBack();
    }

    // ---------- queries (streams) ----------
    public List<Loan> overdueLoans() {
        return members.values().stream()
                .flatMap(m -> m.getLoans().stream())
                .filter(l -> l.isOverdue(today()))
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    public Collection<LibraryItem> allItems() { return items.values(); }
    public Collection<Member> allMembers()    { return members.values(); }

    // ---------- persistence ----------
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        List<String> itemLines = new ArrayList<>();
        for (LibraryItem i : items.values())
            itemLines.add(String.join(",", i.type(), i.getId(), i.getTitle(), i.extra()));
        Files.write(dir.resolve("items.csv"), itemLines);

        List<String> memberLines = new ArrayList<>();
        List<String> loanLines = new ArrayList<>();
        for (Member m : members.values()) {
            memberLines.add(m.getId() + "," + m.getName());
            for (Loan l : m.getLoans())
                loanLines.add(m.getId() + "," + l.getItem().getId() + "," + l.getBorrowedOn());
        }
        Files.write(dir.resolve("members.csv"), memberLines);
        Files.write(dir.resolve("loans.csv"), loanLines);
    }

    public void load(Path dir) throws IOException {
        Path itemsFile = dir.resolve("items.csv");
        if (!Files.exists(itemsFile)) return;            // first run: nothing saved yet

        for (String line : Files.readAllLines(itemsFile)) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            // EXPLAIN: the TYPE column tells us which subclass to construct (a tiny factory)
            LibraryItem item = p[0].equals("BOOK")
                    ? new Book(p[1], p[2], p[3])
                    : new Dvd(p[1], p[2], Integer.parseInt(p[3]));
            items.put(item.getId(), item);
        }
        for (String line : Files.readAllLines(dir.resolve("members.csv"))) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            members.put(p[0], new Member(p[0], p[1]));
        }
        Path loansFile = dir.resolve("loans.csv");
        if (Files.exists(loansFile)) {
            for (String line : Files.readAllLines(loansFile)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                Member m = members.get(p[0]);
                LibraryItem item = items.get(p[1]);
                try {
                    item.borrow(m);                      // marks item unavailable
                    m.addLoan(new Loan(item, m, LocalDate.parse(p[2])));
                } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                    throw new IOException("Corrupt data file: " + e.getMessage(), e);
                }
            }
        }
    }
}
