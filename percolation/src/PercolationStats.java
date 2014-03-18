public class PercolationStats {
    private double[] tresholds;
    private int n;
    private int t;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.t = t;
        tresholds = new double[t];

        run();
    }

    public double mean() {
        return StdStats.mean(tresholds);
    }

    public double stddev() {
        return StdStats.stddev(tresholds);
    }

    public double confidenceLo() {
        double sqrtT = Math.sqrt(t);
        return mean() - (1.96d * stddev() / sqrtT);
    }

    public double confidenceHi() {
        double sqrtT = Math.sqrt(t);
        return mean() + (1.96d * stddev() / sqrtT);
    }

    private void run() {
        for (int i = 0; i < t; i++) {
            int openedSites = 0;
            Percolation p = new Percolation(n);
            while (!p.percolates()) {

                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                    openedSites++;
                }
            }

            tresholds[i] = (double) openedSites / (double) (n * n);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        new PercolationStats(n, t);
    }
}
