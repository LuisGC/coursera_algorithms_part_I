

/**
 * @author luisgc
 */
public class PercolationBruteForce {

    private static int size;
    private static int[][] grid;
    private static boolean[][] checked;

    /**
     * creates N-by-N grid, with all sites blocked
     * 
     * @param initialSize
     *            initial size
     */
    public PercolationBruteForce(int initialSize) {
        grid = new int[initialSize][initialSize];
        size = initialSize;
    }

    /**
     * open site (row i, column j) if it is not already.
     * 
     * @param row
     * @param column
     */
    public void open(int row, int column) {
        grid[row - 1][column - 1] = 1;
    }

    /**
     * is site (row i, column j) open?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isOpen(int row, int column) {
        return grid[row - 1][column - 1] == 1;
    }

    /**
     * is site (row i, column j) full?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isFull(int row, int column) {
        checkIndexInbounds(row, column);
        boolean isOpen = isOpen(row, column);
        if (!isOpen || checked[row - 1][column - 1]) {
            return false;
        }
        if (row == 1 && isOpen) {
            return true;
        }

        boolean isFull = false;
        checked[row - 1][column - 1] = true;

        // checking the upper space
        isFull = isFull(row - 1, column);

        // checking the left space
        if (!isFull && column != 1) {
            isFull = isFull(row, column - 1);
        }

        // checking the right space
        if (!isFull && column != size) {
            isFull = isFull(row, column + 1);
        }

        // checking the down space
        if (!isFull && row != size) {
            isFull = isFull(row + 1, column);
        }

        return isFull;
    }

    /**
     * does the system percolate?
     * 
     * @return
     */
    public boolean percolates() {
        boolean percolates = false;
        checked = new boolean[size][size];

        for (int i = 1; i < size; i++) {
            if (isFull(size, i)) {
                percolates = true;
                break;
            }
        }
        return percolates;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        PercolationBruteForce perc = new PercolationBruteForce(5);
        perc.open(1, 3);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(4, 2);
        // perc.open(4, 3);
        perc.open(5, 2);
        printGrid();

        System.out.println("PERCOLATES? " + perc.percolates());

    }

    private static void printGrid() {
        for (int i = 0; i < size; i++) {
            StringBuilder rowString = new StringBuilder();
            for (int j = 0; j < size; j++) {
                rowString.append(grid[i][j]);
            }
            System.out.println("row_" + (i + 1) + ": " + rowString.toString());
        }
        System.out.println();
    }

    private void checkIndexInbounds(int row, int column) {

        if (row < 1 || row > size || column < 1 || column > size) {
            throw new IndexOutOfBoundsException("spot " + row + "," + column
                    + "is out of bounds");
        }

    }

}
