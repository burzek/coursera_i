public class Subset {
    private RandomizedQueue<String> rq = new RandomizedQueue();

    public Subset() {
    }

    private void run(int k) {
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Subset s = new Subset();
        s.run(k);
    }

}
