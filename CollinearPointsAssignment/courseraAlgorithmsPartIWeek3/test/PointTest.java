import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Point class.
 * 
 * @author luisgc
 */
public class PointTest {

    private Point point;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        point = new Point(5, 5);
    }

    /**
     * Test method for {@link Point#Point(int, int)}.
     */
    @Test
    public final void testConstructor() {
        assertNotNull(point);
    }

    /**
     * Test method for {@link Point#compareTo(Point)}.
     */
    @Test
    public final void testCompareTo() {

        final Point samePoint = new Point(5, 5);
        assertEquals(0, this.point.compareTo(samePoint));

        final Point pointUp = new Point(5, 10);
        assertEquals(-1, this.point.compareTo(pointUp));

        final Point pointDown = new Point(5, 0);
        assertEquals(1, this.point.compareTo(pointDown));

        final Point pointLeft = new Point(0, 5);
        assertEquals(1, this.point.compareTo(pointLeft));

        final Point pointRight = new Point(0, 5);
        assertEquals(1, this.point.compareTo(pointRight));
    }

    /**
     * Test method for {@link Point#slopeTo(Point)}.
     */
    @Test
    public final void testSlopeTo() {

        final Point samePoint = new Point(5, 5);
        assertEquals(Double.NEGATIVE_INFINITY, this.point.slopeTo(samePoint), 0);

        final Point pointUp = new Point(5, 10);
        assertEquals(Double.POSITIVE_INFINITY, this.point.slopeTo(pointUp), 0);

        final Point pointDown = new Point(5, 0);
        assertEquals(Double.POSITIVE_INFINITY, this.point.slopeTo(pointDown), 0);

        final Point pointLeft = new Point(0, 5);
        assertEquals(0D, this.point.slopeTo(pointLeft), 0);

        final Point pointRight = new Point(0, 5);
        assertEquals(0D, this.point.slopeTo(pointRight), 0);

        final Point pointDownLeft = new Point(2, 2);
        assertEquals(1D, this.point.slopeTo(pointDownLeft), 0);

        final Point pointUpRight = new Point(7, 7);
        assertEquals(1D, this.point.slopeTo(pointUpRight), 0);
    }

}
