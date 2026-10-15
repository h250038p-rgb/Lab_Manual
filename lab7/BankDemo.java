/**
 * Lab 7 - Exercise 4 (Intermediate)
 * BankAccount.withdraw now throws InsufficientFundsException instead of returning false.
 *
 * TRADE-OFFS:
 *  + Exception carries a message and extra data (shortfall) and cannot be silently ignored,
 *    whereas a boolean is easy to forget to check.
 *  + Separates the normal path from the error path -> cleaner calling code.
 *  - Checked exceptions add try/catch boilerplate for every caller.
 *  - Exceptions are slower than a boolean, which matters if "insufficient funds" is a very common,
 *    expected outcome rather than an exceptional one.
 */
class InsufficientFundsException extends Exception {
    private final double shortfall;
    public InsufficientFundsException(double shortfall) {
        super("Insufficient funds: short by " + shortfall);
        this.shortfall = shortfall;
    }
    public double getShortfall() { return shortfall; }
}

class BankAccount {
    private double balance;
    BankAccount(double opening) { this.balance = opening; }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
        balance -= amount;
    }
    double getBalance() { return balance; }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(200);
        try {
            acc.withdraw(50);
            System.out.println("Withdrew 50, balance " + acc.getBalance());
            acc.withdraw(500);                       // too much
            System.out.println("not reached");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage() + " (shortfall " + e.getShortfall() + ")");
        }
    }
}
