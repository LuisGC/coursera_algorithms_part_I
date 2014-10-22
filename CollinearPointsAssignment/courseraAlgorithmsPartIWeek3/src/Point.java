import java.util.Comparator;

/**
 * @author luisgc
 */
public class Point implements Comparable<Point> {

    /**
     * compare points by slope to this point
     */
    public final Comparator<Point> SLOPE_ORDER = new ComparatorBySlope();

    private final int x; // x coordinate
    private final int y; // y coordinate

    /**
     * Construct the point (x, y).
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * draw this point.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * draw the line segment from this point to that point.
     * 
     * @param that
     *            the other point
     */
    public void drawTo(final Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * is this point lexicographically smaller than that point?.
     */
    public int compareTo(final Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * the slope between this point and that point.
     * 
     * @param that
     *            the other point
     * @return
     */
    public double slopeTo(final Point that) {

        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.y == that.y) {
            return 0D;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (Double.valueOf(that.y - this.y))
                    / (Double.valueOf(that.x - this.x));
        }
    }

    /**
     * Comparator Implementation by slope.
     * 
     * @author luisgc
     */
    private class ComparatorBySlope implements Comparator<Point> {

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(Point pointA, Point pointB) {
            double slopeA = slopeTo(pointA);
            double slopeB = slopeTo(pointB);
            return Double.compare(slopeA, slopeB);
        }

    }
}
