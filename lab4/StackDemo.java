import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Lab 4 - Exercise 5 (Challenge)
 * Refactor "Stack extends ArrayList" into "Stack HAS-A ArrayList" (composition).
 *
 * WHAT THE INHERITANCE VERSION WRONGLY EXPOSES:
 * If BadStack extends ArrayList, every ArrayList method becomes public on the stack:
 * add(0, x), remove(index), get(i), set(i, x), sort(...)... A caller can insert in the
 * middle or remove the bottom item, which breaks the last-in-first-out guarantee. A stack
 * is NOT an "is-a" list; it merely USES one. Composition hides the list and exposes only
 * push/pop/peek/isEmpty/size.
 */
class BadStack<T> extends ArrayList<T> {
    void push(T item) { add(item); }
    T pop() { return remove(size() - 1); }
}

class GoodStack<T> {
    private final List<T> items = new ArrayList<>();   // HAS-A, hidden from callers

    public void push(T item) { items.add(item); }

    public T pop() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (items.isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public int size()        { return items.size(); }
}

public class StackDemo {
    public static void main(String[] args) {
        BadStack<String> bad = new BadStack<>();
        bad.push("A"); bad.push("B"); bad.push("C");
        bad.add(0, "SNEAKY");                 // legal on BadStack - breaks LIFO discipline!
        bad.remove(1);                        // removes from the middle - also legal
        System.out.println("BadStack after tampering: " + bad);

        GoodStack<String> good = new GoodStack<>();
        good.push("A"); good.push("B"); good.push("C");
        // good.add(0, "X");                  // COMPILE ERROR: the method simply does not exist
        System.out.println("peek = " + good.peek());
        System.out.println("pop  = " + good.pop());
        System.out.println("pop  = " + good.pop());
        System.out.println("size = " + good.size());
    }
}
