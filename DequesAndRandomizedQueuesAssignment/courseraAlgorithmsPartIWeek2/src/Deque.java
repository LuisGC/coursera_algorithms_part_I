import java.util.Iterator;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a
 * stack and a queue that supports inserting and removing items from either the
 * front or the back of the data structure.
 * 
 * @author luisgc
 */
public class Deque<Item> implements Iterable<Item> {

    private int size; // number of elements in the queue
    private Node first;
    private Node last;

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }

            final Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private class Node {

        private Item item;
        private Node previous;
        private Node next;

        public Node(Item item) {
            super();
            this.item = item;
        }
    }

    /**
     * construct an empty deque.
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * is the deque empty?
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * return the number of items on the deque.
     * 
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * insert the item at the front.
     * 
     * @param item
     *            item to add
     */
    public void addFirst(final Item item) {
        if (item == null) {
            throw new NullPointerException("you can't add a null item to Deque");
        }

        final Node newFirst = new Node(item);

        if (isEmpty()) {
            first = newFirst;
            last = first;
        } else {
            newFirst.previous = null;
            newFirst.next = first;
            first.previous = newFirst;

            first = newFirst;
        }

        size++;
    }

    /**
     * insert the item at the end.
     * 
     * @param item
     *            item to add
     */
    public void addLast(final Item item) {
        if (item == null) {
            throw new NullPointerException("you can't add a null item to Deque");
        }

        final Node newLast = new Node(item);

        if (isEmpty()) {
            last = newLast;
            first = last;
        } else {
            newLast.next = null;
            newLast.previous = last;
            last.next = newLast;

            last = newLast;
        }

        size++;
    }

    /**
     * delete and return the item at the front.
     * 
     * @return Item
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty");
        }

        final Item item = first.item;
        if (first.next != null) {
            first.next.previous = null;
        }
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * delete and return the item at the end.
     * 
     * @return Item
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty");
        }

        final Item item = last.item;
        if (last.previous != null) {
            last.previous.next = null;
        }
        last = last.previous;
        size--;

        if (isEmpty()) {
            first = null;
        }
        return item;
    }

    /**
     * return an iterator over items in order from front to end.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
