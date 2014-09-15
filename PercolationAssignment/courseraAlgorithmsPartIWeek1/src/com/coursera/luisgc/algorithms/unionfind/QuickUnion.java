package com.coursera.luisgc.algorithms.unionfind;

import com.coursera.luisgc.util.ConsoleUtils;

/**
 * @author luisgc
 */
public class QuickUnion implements IQuickUnion {

    /**
     * array.
     */
    private int[] id;

    /**
     * Default constructor.
     *
     * @param size
     *            size
     */
    public QuickUnion(final int size) {
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
     * @return index
     */
    private int root(final int i) {
        int root = i;
        while (root != id[root]) {
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
        QuickUnion qf = new QuickUnion(DEFAULT_ARRAY_SIZE);

        String unionsString = "3-8 8-6 0-8 0-7 1-9 3-4";

        String[] unions = unionsString.split(" ");
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
