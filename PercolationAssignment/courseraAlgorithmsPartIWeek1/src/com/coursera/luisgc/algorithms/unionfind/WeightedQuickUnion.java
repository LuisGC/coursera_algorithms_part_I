package com.coursera.luisgc.algorithms.unionfind;

import com.coursera.luisgc.util.ConsoleUtils;

/**
 * @author luisgc
 */
public class WeightedQuickUnion implements IQuickUnion {

    /**
     * the array.
     */
    private int[] id;

    /**
     * the sizes array.
     */
    private int[] sizes;

    /**
     * Default constructor.
     *
     * @param size
     *            size
     */
    public WeightedQuickUnion(final int size) {
        id = new int[size];
        sizes = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            sizes[i] = 1;
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

        if (rootP == rootQ) {
            return;
        }

        if (sizes[rootP] < sizes[rootQ]) {
            id[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            id[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }

    /**
     * @param args
     *            args
     */
    public static void main(final String[] args) {
        WeightedQuickUnion qf = new WeightedQuickUnion(DEFAULT_ARRAY_SIZE);

        String unionsString = "2-1 0-4 0-5 6-3 7-4 9-4 2-6 2-5 1-8";

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
