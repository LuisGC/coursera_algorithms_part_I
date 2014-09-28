import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author luisgc
 */
public class DequeTest {

    private static final Integer ITEM_ONE = Integer.valueOf(1);
    private static final Integer ITEM_TWO = Integer.valueOf(2);

    Deque<Integer> deque;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        deque = new Deque<Integer>();
    }

    /**
     * Test method for {@link Deque#Deque()}.
     */
    @Test
    public final void testConstructor() {
        assertNotNull(deque);
        assertEquals(0, deque.size());
    }

    /**
     * Test method for {@link Deque#isEmpty()}.
     */
    @Test
    public final void testIsEmpty() {
        assertTrue(deque.isEmpty());
        deque.addFirst(ITEM_ONE);
        assertFalse(deque.isEmpty());
    }

    /**
     * Test method for {@link Deque#size()}.
     */
    @Test
    public final void testSize() {
        assertEquals(0, deque.size());

        deque.addFirst(ITEM_ONE);
        assertEquals(1, deque.size());
        deque.removeFirst();
        assertEquals(0, deque.size());

        deque.addLast(ITEM_TWO);
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    /**
     * Test method for {@link Deque#addFirst(java.lang.Object)}.
     */
    @Test
    public final void testAddFirst() {
        assertEquals(0, deque.size());

        deque.addFirst(ITEM_ONE);
        assertEquals(1, deque.size());
        assertEquals(ITEM_ONE, deque.removeFirst());

        deque.addFirst(ITEM_TWO);
        assertEquals(1, deque.size());
        assertEquals(ITEM_TWO, deque.removeLast());

    }

    /**
     * Test method for {@link Deque#addFirst(java.lang.Object)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testAddFirstNullItem() {
        assertEquals(0, deque.size());

        deque.addFirst(null);
    }

    /**
     * Test method for {@link Deque#addLast(java.lang.Object)}.
     */
    @Test
    public final void testAddLast() {
        assertEquals(0, deque.size());

        deque.addLast(ITEM_ONE);
        assertEquals(1, deque.size());
        assertEquals(ITEM_ONE, deque.removeFirst());

        deque.addLast(ITEM_TWO);
        assertEquals(1, deque.size());
        assertEquals(ITEM_TWO, deque.removeLast());
    }

    /**
     * Test method for {@link Deque#addFirst(java.lang.Object)}.
     */
    @Test(expected = NullPointerException.class)
    public final void testAddLastNullItem() {
        assertEquals(0, deque.size());

        deque.addLast(null);
    }

    /**
     * Test method for {@link Deque#removeFirst()}.
     */
    @Test(expected = NoSuchElementException.class)
    public final void testRemoveFirstEmpty() {
        assertEquals(0, deque.size());

        deque.removeFirst();
    }

    /**
     * Test method for {@link Deque#removeLast()}.
     */
    @Test(expected = NoSuchElementException.class)
    public final void testRemoveLastEmpty() {
        assertEquals(0, deque.size());

        deque.removeLast();
    }

}
