import java.util.Arrays;

/**
 * This class examines 4 points at a time and checks whether they all lie on the
 * same line segment, printing out any such line segments to standard output and
 * drawing them using standard drawing.<br>
 * <br>
 * Brute force implementation.
 * 
 * @author luisgc
 */
public class Brute {

    private Point[] pointArray;

    /**
     * 
     */
    private Brute(final int size) {
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

        Arrays.sort(pointArray);

        for (int i = 0; i < arraySize; i++) {
            final Point p1 = pointArray[i];

            for (int j = i + 1; j < arraySize; j++) {
                final Point p2 = pointArray[j];

                for (int k = j + 1; k < arraySize; k++) {
                    final Point p3 = pointArray[k];

                    if (p1.slopeTo(p2) == p1.slopeTo(p3)) {
                        // At least p1, p2 and p3 are collinear

                        for (int l = k + 1; l < arraySize; l++) {
                            final Point p4 = pointArray[l];

                            if (p1.slopeTo(p2) == p1.slopeTo(p4)) {
                                drawCollinearPoints(p1, p2, p3, p4);
                            }

                        }
                    }

                }

            }
        }
    }

    private void drawCollinearPoints(final Point p1, final Point p2,
            final Point p3, final Point p4) {

        final Point[] collinearSegment = new Point[4];
        collinearSegment[0] = p1;
        collinearSegment[1] = p2;
        collinearSegment[2] = p3;
        collinearSegment[3] = p4;

        Arrays.sort(collinearSegment, p1.SLOPE_ORDER);

        printOutSegment(collinearSegment);
        drawSegment(collinearSegment);

    }

    private void printOutSegment(final Point[] collinearSegment) {

        final StringBuilder outPut = new StringBuilder();

        for (int i = 0; i < collinearSegment.length - 1; i++) {
            outPut.append(collinearSegment[i].toString());
            outPut.append(" -> ");
        }
        outPut.append(collinearSegment[collinearSegment.length - 1].toString());
        System.out.println(outPut.toString());
    }

    private void drawSegment(Point[] collinearSegment) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.RED);
        collinearSegment[0]
                .drawTo(collinearSegment[collinearSegment.length - 1]);
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
        Brute brute = new Brute(size);

        for (int i = 0; i < size; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            brute.addPoint(p, i);
        }

        brute.checkCollinearArray();
    }
}
