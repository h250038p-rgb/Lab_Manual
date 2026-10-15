/**
 * Lab 9 - Exercise 5 (Challenge)
 * enum Coin PENNY(1), NICKEL(5), DIME(10), QUARTER(25); fewest coins for an amount.
 * EXPLAIN: enums can have fields, constructors and methods. The greedy strategy (largest coin
 *          first) is optimal for this particular coin system.
 */
public enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int cents;
    Coin(int cents) { this.cents = cents; }     // enum constructors are implicitly private
    public int getCents() { return cents; }

    static void change(int amount) {
        System.out.print(amount + " cents -> ");
        Coin[] all = Coin.values();
        int totalCoins = 0;
        // go from the largest coin (last constant) down to the smallest
        for (int i = all.length - 1; i >= 0; i--) {
            int count = amount / all[i].cents;
            if (count > 0) {
                System.out.print(count + " x " + all[i] + "  ");
                amount %= all[i].cents;
                totalCoins += count;
            }
        }
        System.out.println("(" + totalCoins + " coins)");
    }

    public static void main(String[] args) {
        change(67);   // 2 quarters, 1 dime, 1 nickel, 2 pennies = 6
        change(99);
        change(5);
    }
}
