/**
 * Lab 6 - Exercise 3 (Intermediate)
 * Abstract Account: concrete deposit(), abstract monthlyFee(); Savings and Current accounts.
 */
abstract class Account {
    protected double balance;
    Account(double opening) { this.balance = opening; }

    // EXPLAIN: concrete method shared by ALL accounts - written once
    void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    // EXPLAIN: each account type charges differently, so the rule is left abstract
    abstract double monthlyFee();

    // Template-style method: uses the abstract one
    void applyMonthlyFee() { balance -= monthlyFee(); }
    double getBalance()    { return balance; }
}
class SavingsAccount extends Account {
    SavingsAccount(double opening) { super(opening); }
    double monthlyFee() { return balance >= 1000 ? 0 : 2.50; }   // free when balance is high
}
class CurrentAccount extends Account {
    CurrentAccount(double opening) { super(opening); }
    double monthlyFee() { return 10.00; }                         // flat fee
}

public class AccountDemo {
    public static void main(String[] args) {
        Account[] accounts = { new SavingsAccount(500), new SavingsAccount(2000), new CurrentAccount(800) };
        for (Account a : accounts) {
            a.deposit(100);
            double fee = a.monthlyFee();
            a.applyMonthlyFee();
            System.out.printf("%-15s fee=%.2f balance=%.2f%n", a.getClass().getSimpleName(), fee, a.getBalance());
        }
    }
}
