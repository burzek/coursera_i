import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedIterator<Item> implements Iterator<Item> {

        private int pos;
        private int maxSize;
        private Item[] iterArr;

        public RandomizedIterator(Item[] arr, int size) {
            if (arr != null && arr.length != 0) {
                iterArr = (Item[]) new Object[size];
                for (int i = 0; i < size; i++) {
                    iterArr[i] = arr[i];
                }
                StdRandom.shuffle(iterArr);
            }

            maxSize = size;
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < maxSize;
        }

        @Override
        public Item next() {
            if (pos == maxSize) {
                throw new NoSuchElementException();
            }
            return iterArr[pos++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] items;
    private int size = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int idx = StdRandom.uniform(size);
        Item ret = items[idx];
        items[idx] = items[size - 1];
        items[size - 1] = null;
        size--;

        if (size < items.length / 4) {
            resize(items.length / 2);
        }

        return ret;


    }
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        return items[idx];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator<Item>(items, size);
    }

        public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        System.err.println(rq);
        System.err.println("dequeue:" + rq.dequeue());
        System.err.println("dequeue:" + rq.dequeue());
        System.err.println("dequeue:" + rq.dequeue());
        System.err.println("dequeue:" + rq.dequeue());
        System.err.println(rq);

        rq = new RandomizedQueue();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        Iterator iter = rq.iterator();
        Iterator iter2 = rq.iterator();
        while (iter.hasNext()) {
            System.err.println("iter:" + iter.next());
        }

        while (iter2.hasNext()) {
            System.err.println("iter:" + iter2.next());
        }

    }


}
