package com.coursera.luisgc.algorithms.unionfind;

/**
 * @author luisgc
 */
public interface IQuickUnion {

    /**
     * Default array size.
     */
    int DEFAULT_ARRAY_SIZE = 10;

    /**
     * @param p
     *            first index
     * @param q
     *            second index
     * @return true if connected, false otherwise
     */
    boolean connencted(int p, int q);

    /**
     * @param p
     *            first index
     * @param q
     *            second index
     */
    void union(int p, int q);

}
