import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class DequeueIterator implements  Iterator<Item> {

        private Node current;

        private DequeueIterator(Node start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item val = current.value;
            current = current.next;
            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item value;
        private Node next;
        private Node prev;
    }
    private Node first;
    private Node last;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return  size;
    }

    public void addFirst(Item value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Node item = new Node();
        item.next = first;
        item.value = value;
        if (first != null) {
            first.prev = item;
        }
        if (last == null) {
            last = item;
        }
        first = item;

        size++;
    }

    public void addLast(Item value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Node item = new Node();
        item.value = value;
        item.prev = last;
        if (last != null) {
            last.next = item;
        }
        if (first == null) {
            first = item;
        }
        last = item;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret  = first.value;
        if (first == last) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return ret;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret = last.value;
        if (last == first) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next.prev = null;
            last.next = null;
        }
        size--;
        return ret;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator(first);
    }

    public static void main(String[] args) {
        Deque <Integer> d = new Deque<Integer>();
        d.addFirst(10);
        d.removeLast();
        d.addFirst(20);
        d.addFirst(30);
        d.removeLast();
        d.removeLast();
        d.addLast(100);
        d.addFirst(200);
        d.removeFirst();
        d.removeFirst();
    }

}
