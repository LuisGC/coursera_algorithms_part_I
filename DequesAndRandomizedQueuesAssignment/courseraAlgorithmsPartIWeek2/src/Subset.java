/**
 * a client program that takes a command-line integer k; reads in a sequence of
 * N strings from standard input using StdIn.readString(); and prints out
 * exactly k of them, uniformly at random. Each item from the sequence can be
 * printed out at most once.
 * 
 * @author luisgc
 */
public class Subset {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final RandomizedQueue<String> randomizedQueue =
                new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        final int k = Integer.parseInt(args[0]);

        for (int i = 0; i < k; i++) {
            final String item = randomizedQueue.dequeue();
            StdOut.println(item);
        }
    }

}
