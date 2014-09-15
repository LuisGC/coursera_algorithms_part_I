package com.coursera.luisgc.algorithms.search;

/**
 * @author luisgc
 */
public class BinarySearch {

    /**
     * @param array
     *            array
     * @param key
     *            key
     * @return index of the required number, or -1 if not found
     */
    public static int binarySearch(final int[] array, final int key) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < array[mid]) {
                hi = mid - 1;
            } else if (key > array[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
