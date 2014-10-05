import java.util.Arrays;

/**
 * @author luisgc
 */
public class Brute {

    Point[] pointArray;

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

        for (int i = 0; i < arraySize - 3; i++) {
            final Point p1 = pointArray[i];
            // Arrays.sort(pointArray, p1.SLOPE_ORDER);

            for (int j = i + 1; j < arraySize - 2; j++) {
                final Point p2 = pointArray[j];

                for (int k = j + 1; k < arraySize - 1; k++) {
                    final Point p3 = pointArray[k];

                    for (int l = k + 1; l < arraySize; l++) {
                        final Point p4 = pointArray[k];

                        checkCollinearPoints(p1, p2, p3, p4);

                    }

                }

            }
        }
    }

    private void checkCollinearPoints(final Point p1, final Point p2,
            final Point p3, final Point p4) {

        if (p1.SLOPE_ORDER.compare(p2, p3) == 0) {
            if (p1.SLOPE_ORDER.compare(p2, p4) == 0) {

                final Point[] collinearSegment = new Point[4];
                collinearSegment[0] = p1;
                collinearSegment[1] = p2;
                collinearSegment[2] = p3;
                collinearSegment[3] = p4;

                Arrays.sort(collinearSegment);

                printOutSegment(collinearSegment);
                drawSegment(collinearSegment);
            }

        }

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
