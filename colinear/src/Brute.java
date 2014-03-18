import java.util.Arrays;

public class Brute {



    public Brute() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();
    }

    private void run(String fileName) {
        In inputFile = new In(fileName);
        int count = inputFile.readInt();
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(inputFile.readInt(), inputFile.readInt());
            points[i].draw();
        }
        Arrays.sort(points);

        for (int i = 0; i < count - 3; i++) {
            for (int j = i + 1; j < count - 2; j++) {
                double slope12 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < count - 1; k++) {
                    double slope23 = points[j].slopeTo(points[k]);
                    if (slope12 != slope23) {
                        continue;
                    }
                    for (int l = k + 1; l < count; l++) {
                        double slope34 = points[k].slopeTo(points[l]);
                        if (slope34 == slope23) {
                            StdOut.println(points[i] + "->" + points[j] + "->" + points[k] + "->" + points[l]);
                            points[i].drawTo(points[l]);
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Brute b = new Brute();
        b.run(args[0]);
    }
}
