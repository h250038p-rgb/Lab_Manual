import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/** Menu-driven front end. Data is loaded on start and saved on exit (folder: data/). */
public class LibraryApp {
    private static final Path DATA_DIR = Path.of("data");

    public static void main(String[] args) {
        Library lib = new Library();
        try {
            lib.load(DATA_DIR);
            System.out.println("Loaded " + lib.allItems().size() + " item(s) and "
                    + lib.allMembers().size() + " member(s).");
        } catch (IOException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }

        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.hasNextLine() ? in.nextLine().trim() : "0";   // EOF -> exit
            try {
                switch (choice) {
                    case "1" -> addItem(lib, in);
                    case "2" -> {
                        System.out.print("Member id: ");   String id = in.nextLine();
                        System.out.print("Member name: "); String name = in.nextLine();
                        lib.registerMember(new Member(id, name));
                        System.out.println("Registered.");
                    }
                    case "3" -> {
                        System.out.print("Member id: "); String m = in.nextLine().trim();
                        System.out.print("Item id: ");   String i = in.nextLine().trim();
                        Loan loan = lib.borrow(m, i);
                        System.out.println("OK: " + loan);
                    }
                    case "4" -> {
                        System.out.print("Item id: ");
                        lib.giveBack(in.nextLine().trim());
                        System.out.println("Returned.");
                    }
                    case "5" -> {
                        List<Loan> overdue = lib.overdueLoans();
                        if (overdue.isEmpty()) System.out.println("No overdue items on " + lib.today());
                        else overdue.forEach(l -> System.out.println("OVERDUE: " + l));
                    }
                    case "6" -> {
                        System.out.print("Search text: ");
                        List<LibraryItem> found = lib.searchByTitle(in.nextLine());
                        if (found.isEmpty()) System.out.println("No matches.");
                        else found.forEach(System.out::println);
                    }
                    case "7" -> {
                        lib.allItems().forEach(System.out::println);
                        lib.allMembers().forEach(System.out::println);
                    }
                    case "8" -> {       // demo helper: lets us test overdue logic without waiting
                        System.out.print("Days to advance: ");
                        lib.advanceDays(Integer.parseInt(in.nextLine().trim()));
                        System.out.println("Library date is now " + lib.today());
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                // EXPLAIN: our checked business-rule exceptions are reported, not crashes
                System.out.println("Refused: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            lib.save(DATA_DIR);
            System.out.println("Data saved. Goodbye.");
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    private static void addItem(Library lib, Scanner in) {
        System.out.print("Type (book/dvd): "); String type = in.nextLine().trim().toLowerCase();
        System.out.print("Id: ");              String id = in.nextLine();
        System.out.print("Title: ");           String title = in.nextLine();
        if (type.equals("book")) {
            System.out.print("Author: ");
            lib.addItem(new Book(id, title, in.nextLine()));
        } else if (type.equals("dvd")) {
            System.out.print("Minutes: ");
            lib.addItem(new Dvd(id, title, Integer.parseInt(in.nextLine().trim())));
        } else {
            throw new IllegalArgumentException("Type must be book or dvd");
        }
        System.out.println("Item added.");
    }

    private static void printMenu() {
        System.out.println("\n--- Library Menu ---");
        System.out.println("1 Add item   2 Register member   3 Borrow   4 Return");
        System.out.println("5 Overdue    6 Search title      7 List all  8 Advance days (demo)  0 Exit");
        System.out.print("Choice: ");
    }
}
