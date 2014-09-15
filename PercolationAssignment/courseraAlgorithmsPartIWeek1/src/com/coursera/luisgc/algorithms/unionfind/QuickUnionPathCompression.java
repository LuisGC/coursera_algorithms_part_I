package com.coursera.luisgc.algorithms.unionfind;

import com.coursera.luisgc.util.ConsoleUtils;

/**
 * @author luisgc
 */
public class QuickUnionPathCompression implements IQuickUnion {

    /**
     * the array.
     */
    private int[] id;

    /**
     * Default constructor.
     *
     * @param size
     *            size
     */
    public QuickUnionPathCompression(final int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    /**
     * returns the root of a node.
     *
     * @param i
     *            index
     * @return root index
     */
    private int root(final int i) {
        int root = i;
        while (root != id[root]) {
            id[root] = id[id[root]];
            root = id[root];
        }
        return root;
    }

    @Override
    public final boolean connencted(final int p, final int q) {
        return root(p) == root(q);
    }

    @Override
    public final void union(final int p, final int q) {
        int rootP = root(p);
        int rootQ = root(q);

        id[rootP] = rootQ;
    }

    /**
     * @param args
     *            args
     */
    public static void main(final String[] args) {
        QuickUnionPathCompression qf =
                new QuickUnionPathCompression(DEFAULT_ARRAY_SIZE);

        final String unionsString = "3-8 8-6 0-8 0-7 1-9 3-4";

        final String[] unions = unionsString.split(" ");
        ConsoleUtils.printStringArray(unions, "REQUESTED UNIONS");

        ConsoleUtils.printStatus(qf.id);

        for (String union : unions) {

            String firstNode = union.split("-")[0];
            String secondNode = union.split("-")[1];
            System.out.println(firstNode + "-" + secondNode + ":");

            qf.union(Integer.parseInt(firstNode), Integer.parseInt(secondNode));
            ConsoleUtils.printStatus(qf.id);
        }

    }

}
