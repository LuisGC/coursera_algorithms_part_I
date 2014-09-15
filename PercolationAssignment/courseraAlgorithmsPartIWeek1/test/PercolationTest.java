import org.junit.Assert;
import org.junit.Test;

/**
 * @author luisgc
 */
public class PercolationTest {

    private static final int SIZE = 5;

    private Percolation perc;

    /**
     * Test method for {@link Percolation#Percolation(int)}.
     */
    @Test
    public final void testConstructor() {

        Assert.assertNull(perc);
        perc = new Percolation(SIZE);
        Assert.assertNotNull(perc);
    }

    /**
     * Test method for {@link Percolation#Percolation(int)}.
     */
    @Test
    public final void testConstructorAllClosed() {

        perc = new Percolation(SIZE);

        for (int row = 1; row <= SIZE; row++) {
            for (int column = 1; column <= SIZE; column++) {
                Assert.assertFalse(perc.isOpen(row, column));
            }
        }
    }

    /**
     * Test method for {@link Percolation#isOpen(int, int)}.
     */
    @Test
    public final void testIsOpen() {

        perc = new Percolation(SIZE);

        Assert.assertFalse(perc.isOpen(SIZE, SIZE));
        perc.open(SIZE, SIZE);
        Assert.assertTrue(perc.isOpen(SIZE, SIZE));
    }

    /**
     * Test method for {@link Percolation#isFull(int, int)}.
     */
    @Test
    public final void testIsFullFirstLine() {
        perc = new Percolation(SIZE);

        Assert.assertFalse(perc.isFull(1, SIZE));
        perc.open(1, SIZE);
        Assert.assertTrue(perc.isFull(1, SIZE));
    }

    /**
     * Test method for {@link Percolation#isFull(int, int)}.
     */
    @Test
    public final void testIsFullOtherLine() {
        perc = new Percolation(SIZE);

        Assert.assertFalse(perc.isFull(2, SIZE));
        perc.open(2, SIZE);
        Assert.assertFalse(perc.isFull(2, SIZE));
        perc.open(1, SIZE);
        Assert.assertTrue(perc.isFull(2, SIZE));
    }

    /**
     * Test method for {@link Percolation#open(int, int)}.
     */
    @Test
    public void testOpen() {

        perc = new Percolation(SIZE);
        perc.open(SIZE, SIZE);
        Assert.assertTrue(perc.isOpen(SIZE, SIZE));

    }

    @Test
    public void testOpenColumn() {

        perc = new Percolation(SIZE);
        perc.open(1, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(2, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(5, 2);
        Assert.assertEquals(true, perc.percolates());

    }

    @Test
    public void testBendedColumn() {

        perc = new Percolation(SIZE);
        perc.open(1, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(2, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 3);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(5, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 3);
        Assert.assertEquals(false, perc.percolates());
        perc.open(2, 3);
        Assert.assertEquals(true, perc.percolates());

    }

    @Test
    public void testBridgeColumn() {

        perc = new Percolation(SIZE + 1);
        perc.open(1, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(2, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 2);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 3);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 4);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 4);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 5);
        Assert.assertEquals(false, perc.percolates());
        perc.open(3, 6);
        Assert.assertEquals(false, perc.percolates());
        perc.open(4, 6);
        Assert.assertEquals(false, perc.percolates());
        perc.open(5, 6);
        Assert.assertEquals(false, perc.percolates());
        perc.open(6, 6);
        Assert.assertEquals(true, perc.percolates());

    }

}
