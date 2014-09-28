import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author luisgc
 */
public class RandomizedQueueTest {

    private static final int MASSIVE_AMOUNT = 50;

    private static final Integer ITEM_ONE = Integer.valueOf(1);

    private RandomizedQueue<Integer> randomizedQueue;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        randomizedQueue = new RandomizedQueue<Integer>();
    }

    /**
     * Test method for {@link RandomizedQueue#RandomizedQueue()}.
     */
    @Test
    public final void testConstructor() {
        assertTrue(randomizedQueue.isEmpty());
    }

    /**
     * Test method for {@link RandomizedQueue#isEmpty()}.
     */
    @Test
    public final void testIsEmpty() {
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.enqueue(ITEM_ONE);
        assertFalse(randomizedQueue.isEmpty());

        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
    }

    /**
     * Test method for {@link RandomizedQueue#size()}.
     */
    @Test
    public final void testSize() {
        assertTrue(randomizedQueue.isEmpty());
        assertEquals(0, randomizedQueue.size());

        randomizedQueue.enqueue(ITEM_ONE);
        assertEquals(1, randomizedQueue.size());

        randomizedQueue.dequeue();
        assertEquals(0, randomizedQueue.size());
    }

    /**
     * Test method for {@link RandomizedQueue#enqueue(java.lang.Object)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testEnqueueNullItem() {
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.enqueue(null);
    }

    /**
     * Test method for {@link RandomizedQueue#dequeue()}.
     */
    @Test(expected = NoSuchElementException.class)
    public final void testDequeueEmpty() {
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.dequeue();
    }

    /**
     * Test method for {@link RandomizedQueue#sample()}.
     */
    @Test
    public final void testSample() {
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.enqueue(ITEM_ONE);
        assertFalse(randomizedQueue.isEmpty());

        randomizedQueue.sample();
        assertFalse(randomizedQueue.isEmpty());
    }

    /**
     * Test method for {@link RandomizedQueue#size()}.
     */
    @Test
    public final void testMassiveEnqueue() {
        assertEquals(0, randomizedQueue.size());

        for (int i = 1; i <= MASSIVE_AMOUNT; i++) {
            randomizedQueue.enqueue(Integer.valueOf(i));
            System.out.println(randomizedQueue.size());
        }
        assertEquals(MASSIVE_AMOUNT, randomizedQueue.size());

        for (int i = 1; i <= MASSIVE_AMOUNT; i++) {
            randomizedQueue.dequeue();
            System.out.println(randomizedQueue.size());
        }
        assertEquals(0, randomizedQueue.size());

    }

    /**
     * Test method for {@link RandomizedQueue#size()}.
     */
    @Test
    public final void testValidIteratorAfterMassiveEnqueue() {
        assertEquals(0, randomizedQueue.size());

        for (int i = 1; i <= MASSIVE_AMOUNT; i++) {
            randomizedQueue.enqueue(Integer.valueOf(i));
        }

        Iterator<Integer> iterator = randomizedQueue.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
