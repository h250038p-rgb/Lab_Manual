import java.util.ArrayList;
import java.util.List;

/**
 * Lab 11 - design patterns from the manual: Singleton, Factory, Strategy, Observer.
 */
// ---------- SINGLETON ----------
class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();   // created once
    private final String institution = "Example University";
    private AppConfig() { }                                       // private ctor blocks 'new'
    public static AppConfig getInstance() { return INSTANCE; }
    public String getInstitution() { return institution; }
}
// ---------- FACTORY ----------
interface Notification { void send(String message); }
class EmailNotification implements Notification {
    public void send(String m) { System.out.println("Email: " + m); }
}
class SmsNotification implements Notification {
    public void send(String m) { System.out.println("SMS: " + m); }
}
class NotificationFactory {
    // EXPLAIN: callers ask by name and receive the interface type - they never write 'new Email...'
    static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms"   -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
// ---------- STRATEGY ----------
interface DiscountStrategy { double apply(double price); }
class Checkout {
    private DiscountStrategy strategy;
    Checkout(DiscountStrategy s) { this.strategy = s; }
    void setStrategy(DiscountStrategy s) { this.strategy = s; }   // change behaviour at run time
    double total(double price) { return strategy.apply(price); }
}
// ---------- OBSERVER ----------
interface GradeListener { void onGradePublished(String course, int mark); }
class GradeBook {
    private final List<GradeListener> listeners = new ArrayList<>();
    void subscribe(GradeListener l) { listeners.add(l); }
    void publish(String course, int mark) {
        for (GradeListener l : listeners) l.onGradePublished(course, mark);   // notify all
    }
}

public class PatternsDemo {
    public static void main(String[] args) {
        System.out.println("--- Singleton ---");
        AppConfig c1 = AppConfig.getInstance(), c2 = AppConfig.getInstance();
        System.out.println("Same instance? " + (c1 == c2) + " -> " + c1.getInstitution());

        System.out.println("--- Factory ---");
        NotificationFactory.create("email").send("Lab 11 is open");
        NotificationFactory.create("sms").send("Lab 11 is open");

        System.out.println("--- Strategy ---");
        Checkout checkout = new Checkout(price -> price);              // no discount
        System.out.println("Regular: " + checkout.total(200));
        checkout.setStrategy(price -> price * 0.90);                   // 10% student discount
        System.out.println("Student: " + checkout.total(200));

        System.out.println("--- Observer ---");
        GradeBook book = new GradeBook();
        book.subscribe((course, mark) -> System.out.println("Student portal: " + course + " = " + mark));
        book.subscribe((course, mark) -> { if (mark < 50) System.out.println("Advisor alert: " + course + " failed"); });
        book.publish("OOP", 72);
        book.publish("Databases", 45);
    }
}
