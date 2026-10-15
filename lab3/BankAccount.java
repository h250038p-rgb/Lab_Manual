import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lab 3 - Exercise 3 (Intermediate)
 * BankAccount extended with a transaction history; getter returns an UNMODIFIABLE copy.
 */
public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final List<String> history = new ArrayList<>();   // private mutable state

    public BankAccount(String accountNumber, double openingBalance) {
        if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
        history.add("OPEN " + openingBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        history.add("DEPOSIT " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) {
            history.add("FAILED WITHDRAW " + amount);
            return false;
        }
        balance -= amount;
        history.add("WITHDRAW " + amount);
        return true;
    }

    public double getBalance() { return balance; }

    // EXPLAIN: returning 'history' directly would let callers add/remove entries (a leak).
    //          We copy it, then wrap the copy as read-only: the caller can look but never alter,
    //          and later changes to the account do not silently change a list they already hold.
    public List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", 100);
        acc.deposit(50);
        acc.withdraw(30);
        acc.withdraw(1000);

        List<String> h = acc.getHistory();
        h.forEach(System.out::println);

        try {
            h.add("FAKE DEPOSIT 1000000");        // attempt to tamper
        } catch (UnsupportedOperationException e) {
            System.out.println("Tampering blocked: history is read-only");
        }
        System.out.println("Balance: " + acc.getBalance());
    }
}
