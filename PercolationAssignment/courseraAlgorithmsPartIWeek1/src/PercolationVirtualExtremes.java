


/**
 * @author luisgc
 */
public class PercolationVirtualExtremes {

    private static int virtualTopIndex;
    private static int virtualBottomIndex;

    private static int size;
    private static int[] gridStates;

    private WeightedQuickUnionUF gridUnions;

    /**
     * creates N-by-N grid, with all sites blocked
     * 
     * @param initialSize
     *            initial size
     */
    public PercolationVirtualExtremes(final int initialSize) {

        size = initialSize;

        // We are adding a virtual top and bottom
        gridStates = new int[initialSize * initialSize + 2];
        gridUnions = new WeightedQuickUnionUF(initialSize * initialSize + 2);

        // both virtual nodes are open
        virtualTopIndex = initialSize * initialSize;
        virtualBottomIndex = initialSize * initialSize + 1;
        gridStates[virtualTopIndex] = 1;
        gridStates[virtualBottomIndex] = 1;
    }

    /**
     * open site (row i, column j) if it is not already.
     * 
     * @param row
     * @param column
     */
    public void open(final int row, final int column) {
        checkIndexInbounds(row, column);
        if (isOpen(row, column)) {
            return;
        }

        // we open the cell
        final int cellIndex = getCellIndex(row, column);

        gridStates[cellIndex] = 1;

        if (row != 1 && isOpen(row - 1, column)) {
            union(cellIndex, getCellIndex(row - 1, column));
        } else if (row == 1) { // top row
            union(cellIndex, virtualTopIndex);
        }

        if (row != size && isOpen(row + 1, column)) {
            union(cellIndex, getCellIndex(row + 1, column));
        } else if (row == size) { // bottom row
            union(cellIndex, virtualBottomIndex);
        }

        if (column != 1 && isOpen(row, column - 1)) {
            union(cellIndex, getCellIndex(row, column - 1));
        }

        if (column != size && isOpen(row, column + 1)) {
            union(cellIndex, getCellIndex(row, column + 1));
        }
    }

    /**
     * is site (row i, column j) open?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isOpen(final int row, final int column) {
        checkIndexInbounds(row, column);
        return gridStates[getCellIndex(row, column)] == 1;
    }

    /**
     * is site (row i, column j) full?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isFull(final int row, final int column) {
        checkIndexInbounds(row, column);
        return gridUnions.connected(virtualTopIndex, getCellIndex(row, column));
    }

    /**
     * does the system percolate?
     * 
     * @return
     */
    public boolean percolates() {
        return gridUnions.connected(virtualTopIndex, virtualBottomIndex);
    }

    public static void printGrid() {
        for (int i = 0; i < size; i++) {
            final StringBuilder rowString = new StringBuilder();
            for (int j = 0; j < size; j++) {
                rowString.append(gridStates[i * size + j]);
            }
            System.out.println("row_" + (i + 1) + ": " + rowString.toString());
        }
        System.out.println();
    }

    private void union(final int row, final int column) {
        if (!gridUnions.connected(row, column)) {
            gridUnions.union(row, column);
        }
    }

    private void checkIndexInbounds(final int row, final int column) {

        if (row < 1 || row > size || column < 1 || column > size) {
            throw new IndexOutOfBoundsException("spot " + row + "," + column
                    + "is out of bounds");
        }
    }

    private int getCellIndex(final int row, final int column) {
        return (row - 1) * size + column - 1;
    }

}
