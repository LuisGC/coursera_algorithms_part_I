package com.coursera.luisgc.algorithms.unionfind;

import com.coursera.luisgc.util.ConsoleUtils;

/**
 * @author luisgc
 */
public class QuickFind implements IQuickUnion {

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
    public QuickFind(final int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }

    }

    @Override
    public final boolean connencted(final int p, final int q) {
        return id[p] == id[q];
    }

    @Override
    public final void union(final int p, final int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    /**
     * @param args
     *            args
     */
    public static void main(final String[] args) {
        QuickFind qf = new QuickFind(DEFAULT_ARRAY_SIZE);

        String unionsString = "2-3 0-1 4-1 0-6 8-5 5-9";

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
