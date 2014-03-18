import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fast {

    public Fast() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();
    }

	private void foundColinear(Point[] colinear, Map<Point, List<Point>> colinearPointsCache) {

			Arrays.sort(colinear);

			List<Point> endPoints = colinearPointsCache.get(colinear[0]);
			boolean cacheHit = false;
			if (endPoints != null) {
				for (int s = 0; s < endPoints.size(); s++) {
					if (endPoints.get(s).equals(colinear[colinear.length - 1])) {
						//cache hit!
						cacheHit = true;
					}
				}
			}

			if (!cacheHit) {
				endPoints = endPoints == null ? new ArrayList() : endPoints;
				endPoints.add(colinear[colinear.length - 1]);
				colinearPointsCache.put(colinear[0], endPoints);

				for (int iter = 0; iter < colinear.length; iter++) {
					StdOut.print(colinear[iter]);
					if (iter < colinear.length - 1) {
						StdOut.print(" -> ");
					} else {
						StdOut.println();
					}

				}

				colinear[0].drawTo(colinear[colinear.length - 1]);
			}

	}

    private void run(String fileName) {
        Map<Point, List<Point>> colinearPointsCache = new HashMap();

        In inputFile = new In(fileName);
        int count = inputFile.readInt();
        Point[] points = new Point[count];
        Point[] slopePoints = new Point[count];


        for (int i = 0; i < count; i++) {
            points[i] = new Point(inputFile.readInt(), inputFile.readInt());
            slopePoints[i] = points[i];
            points[i].draw();
        }
        Arrays.sort(points);

        for (int i = 0; i < count; i++) {
            Point basePoint = points[i];
            Arrays.sort(slopePoints, 0, count, basePoint.SLOPE_ORDER);	//first is -NEG_INF

		    int from = 1;
            while (from < count) {
                int step = from;
                while (step < count - 1 && basePoint.slopeTo(slopePoints[step]) == basePoint.slopeTo(slopePoints[step + 1])) {
                    step++;
                }

                int slopeLength = step - from + 1;
                if (slopeLength > 2) {
					Point[] out = new Point[slopeLength + 1];
                    for (int iter = 0; iter < slopeLength; iter++) {
                        out[iter] = slopePoints[from + iter];
                    }
                    out[slopeLength] = basePoint;
                    foundColinear(out, colinearPointsCache);
                }
                from = step + 1;
            }



        }

    }

    public static void main(String[] args) {
        Fast b = new Fast();
        b.run(args[0]);

    }
}
