package com.coursera.luisgc.util;

/**
 * @author luisgc
 */
public final class ConsoleUtils {

    /**
     * @param array
     *            array
     * @param label
     *            label to precede
     */
    public static void
            printStringArray(final String[] array, final String label) {
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            arrayString.append(array[i]).append(", ");
        }
        System.out.println(label + ":\n" + arrayString.toString() + "\n");
    }

    /**
     * Prints the status of an array.
     *
     * @param array
     *            array
     */
    public static void printStatus(final int[] array) {
        StringBuilder posString = new StringBuilder();
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            posString.append(i).append(" ");
            arrayString.append(array[i]).append(" ");
        }
        System.out.println("POS ----> " + posString.toString());
        System.out.println("ARRAY --> " + arrayString.toString());
        System.out.println();
    }

}
