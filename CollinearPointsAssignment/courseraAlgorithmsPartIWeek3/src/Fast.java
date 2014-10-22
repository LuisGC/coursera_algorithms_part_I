import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class examines 4 points at a time and checks whether they all lie on the
 * same line segment, printing out any such line segments to standard output and
 * drawing them using standard drawing.<br>
 * <br>
 * This implementation is faster than the Brute.java.
 * 
 * @author luisgc
 */
public class Fast {

    private Point[] pointArray;

    /**
     * 
     */
    private Fast(final int size) {
        pointArray = new Point[size];
    }

    private void addPoint(final Point point, final int position) {
        pointArray[position] = point;
    }

    private void checkCollinearArray() {
        if (pointArray == null) {
            throw new IllegalArgumentException();
        }

        int arraySize = pointArray.length;
        if (arraySize < 4) {
            return;
        }

        drawEntireArray();

        // Sorting the array by coordinates
        Arrays.sort(pointArray);

        ArrayList<ArrayList<Point>> segments =
                new ArrayList<ArrayList<Point>>();

        for (int i = 0; i < arraySize; i++) {
            final Point p1 = pointArray[i];

            // searching for the lower and higher bounds
            Arrays.sort(pointArray, p1.SLOPE_ORDER);
            int lo = 0;
            int hi = 0;

            for (int j = 1; j < arraySize - 1; j++) {
                if (lo == 0) {
                    if (p1.slopeTo(pointArray[j]) == p1
                            .slopeTo(pointArray[j + 1])) {
                        lo = j;
                    }
                } else {
                    if (p1.slopeTo(pointArray[j]) != p1
                            .slopeTo(pointArray[j + 1])) {
                        hi = j;
                        // } else if (j == arraySize - 2) {
                        // hi = arraySize - 1;
                    }
                }

                if (lo > 0 && hi > 0) {
                    if (hi - lo > 1) {
                        final Point[] range =
                                Arrays.copyOfRange(pointArray, lo, hi + 1);
                        final ArrayList<Point> array = new ArrayList<Point>(0);
                        Collections.addAll(array, range);
                        array.add(p1);
                        Collections.sort(array);
                        segments.add(array);
                    }
                    lo = 0;
                    hi = 0;
                }

            }

            // re-sorting
            Arrays.sort(pointArray);
        }

        // removing duplicates
        HashSet<ArrayList<Point>> hs = new HashSet<ArrayList<Point>>();
        hs.addAll(segments);
        segments.clear();
        segments.addAll(hs);

        // draw lines
        Iterator<ArrayList<Point>> i = segments.iterator();
        while (i.hasNext()) {
            ArrayList<Point> collinearSegment = i.next();

            printOutSegment(collinearSegment);
            drawSegment(collinearSegment);
        }

        StdDraw.show(0);

    }

    private void printOutSegment(final ArrayList<Point> collinearSegment) {

        final StringBuilder outPut = new StringBuilder();

        for (int i = 0; i < collinearSegment.size() - 1; i++) {
            outPut.append(collinearSegment.get(i).toString());
            outPut.append(" -> ");
        }
        outPut.append(collinearSegment.get(collinearSegment.size() - 1)
                .toString());
        System.out.println(outPut.toString());
    }

    private void drawSegment(ArrayList<Point> collinearSegment) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.RED);
        collinearSegment.get(0).drawTo(
                collinearSegment.get(collinearSegment.size() - 1));
    }

    private void drawEntireArray() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (Point p : pointArray) {
            p.draw();
        }
    }

    /**
     * Read the points from an input file in the following format: An integer N,
     * followed by N pairs of integers (x, y), each between 0 and 32,767.
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        // read in the input
        final String filename = args[0];
        final In in = new In(filename);

        final int size = in.readInt();
        Fast brute = new Fast(size);

        for (int i = 0; i < size; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            brute.addPoint(p, i);
        }

        brute.checkCollinearArray();
    }
}
