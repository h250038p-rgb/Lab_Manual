import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lab 9 - Exercise 4 (Intermediate)
 * LinkedList with a private static nested Node and an inner Iterator class,
 * so the list works in a for-each loop.
 */
public class MyLinkedList<T> implements Iterable<T> {

    // EXPLAIN: static nested -> does not need an enclosing list object; private -> an implementation detail
    private static class Node<E> {
        E value;
        Node<E> next;
        Node(E value) { this.value = value; }
    }

    private Node<T> head, tail;
    private int size;

    public void add(T value) {
        Node<T> n = new Node<>(value);
        if (head == null) head = tail = n;
        else { tail.next = n; tail = n; }
        size++;
    }
    public int size() { return size; }

    // EXPLAIN: Iterable requires iterator(); for-each loops call it behind the scenes
    @Override public Iterator<T> iterator() { return new ListIterator(); }

    // EXPLAIN: INNER (non-static) class -> belongs to one list and reads its 'head' directly
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;
        @Override public boolean hasNext() { return current != null; }
        @Override public T next() {
            if (current == null) throw new NoSuchElementException();
            T v = current.value;
            current = current.next;
            return v;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("alpha"); list.add("beta"); list.add("gamma");
        for (String s : list) System.out.println(s);     // works thanks to Iterable
        System.out.println("size = " + list.size());
    }
}
