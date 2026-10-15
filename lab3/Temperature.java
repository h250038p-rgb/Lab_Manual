/**
 * Lab 3 - Exercise 5 (Challenge)
 * Stores KELVIN internally but offers Celsius/Fahrenheit. Rejects values below absolute zero.
 *
 * WHY CHANGING THE INTERNAL UNIT WOULD NOT BREAK CALLERS:
 * Callers only use getCelsius()/getFahrenheit()/getKelvin() and the static factories.
 * The 'kelvin' field is private, so if we switched to storing Celsius, only the bodies of
 * this class would change - every line of caller code would keep compiling and behaving the same.
 */
public class Temperature {
    private static final double ABSOLUTE_ZERO_K = 0.0;
    private final double kelvin;              // hidden internal representation

    private Temperature(double kelvin) {
        if (kelvin < ABSOLUTE_ZERO_K) {
            throw new IllegalArgumentException("Temperature is below absolute zero (0 K = -273.15 C)");
        }
        this.kelvin = kelvin;
    }

    public static Temperature ofKelvin(double k)     { return new Temperature(k); }
    public static Temperature ofCelsius(double c)    { return new Temperature(c + 273.15); }
    public static Temperature ofFahrenheit(double f) { return new Temperature((f - 32) * 5 / 9 + 273.15); }

    public double getKelvin()     { return kelvin; }
    public double getCelsius()    { return kelvin - 273.15; }
    public double getFahrenheit() { return getCelsius() * 9 / 5 + 32; }

    public static void main(String[] args) {
        Temperature t = Temperature.ofCelsius(25);
        System.out.printf("%.2f C = %.2f F = %.2f K%n", t.getCelsius(), t.getFahrenheit(), t.getKelvin());
        try {
            Temperature.ofCelsius(-300);      // below -273.15 C
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
