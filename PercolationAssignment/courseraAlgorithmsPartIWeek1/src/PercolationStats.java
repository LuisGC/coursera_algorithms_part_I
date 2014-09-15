
/**
 * @author luisgc
 */
public class PercolationStats {

    private int originalSize;
    private int originalNumberOfExperiments;
    private boolean[] gridOpenedCells;
    private double[] thresholdPerExperiment;

    /**
     * perform T independent computational experiments on an N-by-N grid.
     * 
     * @param defaultSize
     *            default size
     * @param numberOfExperiments
     *            number of experiments
     */
    public PercolationStats(final int defaultSize, final int numberOfExperiments) {

        if (defaultSize <= 0 || numberOfExperiments <= 0) {
            throw new IllegalArgumentException();
        }

        originalSize = defaultSize;
        final double totalSize = Double.valueOf(originalSize * originalSize);
        originalNumberOfExperiments = numberOfExperiments;

        thresholdPerExperiment = new double[numberOfExperiments];

        for (int i = 0; i < numberOfExperiments; i++) {
            final Percolation percolation = new Percolation(defaultSize);
            gridOpenedCells = new boolean[defaultSize * defaultSize];
            int openedCells = 0;

            do {
                final int index = obtainNextRandomIndex();

                percolation.open(getRowFromIndex(index),
                        getColumnFromIndex(index));
                gridOpenedCells[index] = true;
                openedCells++;

            } while (!percolation.percolates());

            thresholdPerExperiment[i] = Double.valueOf(openedCells) / totalSize;
        }
    }

    /**
     * Method to obtain the next uniform random index from a blocked cell
     * 
     * @return a uniform random number
     */
    private int obtainNextRandomIndex() {

        int uniformRandomNumber;
        do {
            uniformRandomNumber =
                    StdRandom.uniform(0, originalSize * originalSize);
        } while (gridOpenedCells[uniformRandomNumber]);

        return uniformRandomNumber;
    }

    /**
     * @return sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(thresholdPerExperiment);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        if (originalNumberOfExperiments > 1) {
            return StdStats.stddev(thresholdPerExperiment);
        } else {
            return Double.NaN;
        }
    }

    /**
     * @return returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean()
                - (1.96 * stddev() / Math.sqrt(originalNumberOfExperiments));
    }

    /**
     * @return returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean()
                + (1.96 * stddev() / Math.sqrt(originalNumberOfExperiments));
    }

    public static void main(String[] args) {

        checkArguments(args);
        String defaultSize = args[0];
        String numberOfExperiments = args[1];

        final PercolationStats perStats =
                new PercolationStats(Integer.parseInt(defaultSize),
                        Integer.parseInt(numberOfExperiments));
        System.out.println("mean                    = " + perStats.mean());
        System.out.println("stddev                  = " + perStats.stddev());
        System.out.println("95% confidence interval = "
                + perStats.confidenceLo() + ", " + perStats.confidenceHi());

    }

    /**
     * Checks if there are at least two arguments and both of them are numeric
     * and greater than 0
     * 
     * @param args
     */
    private static void checkArguments(final String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        try {
            final int firstArg = Integer.parseInt(args[0]);
            final int secondArg = Integer.parseInt(args[1]);

            if (firstArg <= 0 || secondArg <= 0) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

    }

    private int getRowFromIndex(final int index) {
        return 1 + (index / originalSize);
    }

    private int getColumnFromIndex(final int index) {
        return 1 + (index % originalSize);
    }

}
