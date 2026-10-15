import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Lab 8 - Exercise 3 (Intermediate)
 * Generic Stack<T> backed by ArrayList<T>: push, pop, peek, isEmpty.
 * EXPLAIN: T is a type parameter - one class works for Integer, String, anything, with
 *          compile-time type safety and no casts.
 */
public class MyStack<T> {
    private final List<T> items = new ArrayList<>();

    public void push(T item) { items.add(item); }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public static void main(String[] args) {
        MyStack<String> s = new MyStack<>();
        s.push("one"); s.push("two"); s.push("three");
        System.out.println("peek = " + s.peek());
        while (!s.isEmpty()) System.out.println("pop  = " + s.pop());

        MyStack<Integer> n = new MyStack<>();
        n.push(10); n.push(20);
        int top = n.pop();             // no cast needed: compiler knows it is Integer
        System.out.println("top int = " + top);
        // n.push("text");             // COMPILE ERROR: type safety
        try { n.pop(); n.pop(); } catch (EmptyStackException e) { System.out.println("Stack empty!"); }
    }
}
