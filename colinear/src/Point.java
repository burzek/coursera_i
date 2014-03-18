import java.util.Comparator;



public class Point implements Comparable<Point> {

    private class PointSlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double ret = Point.this.slopeTo(o1) - Point.this.slopeTo(o2);
            if (ret > 0) {
                return 1;
            } else if (ret < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public final Comparator<Point> SLOPE_ORDER = new PointSlopeComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        } else  if (y == that.y) {
            return 0.0;
        }
        return ((double) that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
       int ret = y - that.y;
        if (ret == 0) {
            return x - that.x;
        }
        return ret;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {

    }

}