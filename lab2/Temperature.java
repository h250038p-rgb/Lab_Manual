/**
 * Lab 2 - Exercise 5 (Challenge)
 * Static factory methods with a private constructor.
 *
 * WHY STATIC FACTORIES CAN BE CLEARER THAN OVERLOADED CONSTRUCTORS:
 *  - Both units are a single double, so new Temperature(double) and another
 *    new Temperature(double) cannot be overloaded - the signatures clash.
 *  - Factories have NAMES: fromCelsius(25) says what the number means; new Temperature(25) does not.
 *  - They can validate, cache or return subtypes, and the class controls all creation.
 */
public class Temperature {
    private final double celsius;          // single internal representation

    // EXPLAIN: private -> outsiders must use the named factories
    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double c)    { return new Temperature(c); }
    public static Temperature fromFahrenheit(double f) { return new Temperature((f - 32) * 5.0 / 9.0); }

    public double getCelsius()    { return celsius; }
    public double getFahrenheit() { return celsius * 9.0 / 5.0 + 32; }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", getCelsius(), getFahrenheit());
    }

    public static void main(String[] args) {
        Temperature t1 = Temperature.fromCelsius(100);
        Temperature t2 = Temperature.fromFahrenheit(98.6);
        System.out.println(t1);   // 100.0 C / 212.0 F
        System.out.println(t2);   // 37.0 C / 98.6 F
        // new Temperature(5);    // COMPILE ERROR: constructor is private
    }
}
