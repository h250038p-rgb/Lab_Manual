/**
 * Lab 5 - Exercise 5 (Challenge)
 * Design A: PaymentProcessor.pay() uses instanceof pattern matching.
 * Design B: refactored to a polymorphic process() method.
 *
 * COMPARISON:
 *  A (instanceof chain): all type knowledge sits in ONE place, but every new payment type forces
 *    an edit to the processor (violates Open/Closed); forgetting a branch is a silent bug.
 *  B (polymorphism): each class owns its behaviour; adding BankTransfer means adding ONE class and
 *    changing nothing else. Preferred whenever behaviour belongs to the type itself.
 */
abstract class Payment {
    protected final double amount;
    Payment(double amount) { this.amount = amount; }
    // Used by design B only
    abstract void process();
}
class CardPayment extends Payment {
    final String cardNumber;
    CardPayment(double amount, String card) { super(amount); this.cardNumber = card; }
    @Override void process() {
        System.out.println("[B] Charging card ****" + cardNumber.substring(cardNumber.length() - 4)
                + " for $" + amount);
    }
}
class MobileMoneyPayment extends Payment {
    final String phone;
    MobileMoneyPayment(double amount, String phone) { super(amount); this.phone = phone; }
    @Override void process() {
        System.out.println("[B] Sending mobile-money request to " + phone + " for $" + amount);
    }
}

// ---------- Design A ----------
class PaymentProcessorA {
    void pay(Payment p) {
        // EXPLAIN: pattern matching - tests the type AND binds a typed variable in one step
        if (p instanceof CardPayment c) {
            System.out.println("[A] Charging card ****" + c.cardNumber.substring(c.cardNumber.length() - 4)
                    + " for $" + c.amount);
        } else if (p instanceof MobileMoneyPayment m) {
            System.out.println("[A] Sending mobile-money request to " + m.phone + " for $" + m.amount);
        } else {
            throw new IllegalArgumentException("Unknown payment type");
        }
    }
}

// ---------- Design B ----------
class PaymentProcessorB {
    void pay(Payment p) { p.process(); }     // no type checks at all
}

public class PaymentDemo {
    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(100, "1234567812345678"),
            new MobileMoneyPayment(25.5, "+263771234567")
        };
        PaymentProcessorA a = new PaymentProcessorA();
        PaymentProcessorB b = new PaymentProcessorB();
        for (Payment p : payments) { a.pay(p); }
        for (Payment p : payments) { b.pay(p); }
    }
}
