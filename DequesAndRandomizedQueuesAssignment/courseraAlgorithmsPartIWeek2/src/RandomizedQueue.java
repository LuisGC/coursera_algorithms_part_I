import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 * 
 * @author luisgc
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;

    public class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] iteratorArray;

        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
            iteratorArray = (Item[]) new Object[1];
            for (int i = 0; i < size; i++) {
                iteratorArray[i] = array[i];
            }
            StdRandom.shuffle(iteratorArray);
        }

        @Override
        public boolean hasNext() {
            return iteratorArray.length > 0;
        }

        @Override
        public Item next() {
            if (iteratorArray.length == 0) {
                throw new NoSuchElementException();
            }
            Item nextItem = iteratorArray[iteratorArray.length - 1];
            iteratorArray[iteratorArray.length - 1] = null;

            return nextItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * construct an empty randomized queue.
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        size = 0;
        array = (Item[]) new Object[1];
    }

    /**
     * is the queue empty?
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * return the number of items on the queue.
     * 
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     * 
     * @param item
     *            item to add
     */
    public void enqueue(final Item item) {
        if (item == null)
            throw new NullPointerException(
                    "you can't add a null item to RandomizedQueue");

        if (size == array.length) {
            resize(2 * array.length);
        }
        array[size++] = item;
    }

    private void resize(final int capacity) {
        @SuppressWarnings("unchecked")
        final Item[] resizedArray = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            resizedArray[i] = array[i];
        }
        array = resizedArray;
    }

    /**
     * delete and return a random item.
     * 
     * @return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException(
                    "The RandomizedQueue is empty");
        }

        Item removedItem;
        if (size == 1) {
            removedItem = array[--size];
        } else {
            final int randomIndex = StdRandom.uniform(size - 1);
            removedItem = array[randomIndex];
            array[randomIndex] = array[--size];
            if (array.length / size <= 4) {
                resize(array.length / 2);
            }
        }

        return removedItem;
    }

    /**
     * return (but do not delete) a random item.
     * 
     * @return a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("The RandomizedQueue is empty");
        }

        final int randomIndex = StdRandom.uniform(size);
        return array[randomIndex];
    }

    /**
     * return an independent iterator over items in random order
     * 
     * @return iterator
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
