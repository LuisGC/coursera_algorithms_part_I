
/**
 * @author luisgc
 */
public class PercolationVirtualExtremeTop {

    private static int virtualTopIndex;

    private static int size;
    private static boolean[] gridStates;

    private WeightedQuickUnionUF gridUnions;

    /**
     * creates N-by-N grid, with all sites blocked
     * 
     * @param initialSize
     *            initial size
     */
    public PercolationVirtualExtremeTop(final int initialSize) {

        size = initialSize;

        // We are adding a virtual top and bottom
        gridStates = new boolean[initialSize * initialSize + 1];
        gridUnions = new WeightedQuickUnionUF(initialSize * initialSize + 1);

        // both virtual nodes are open
        virtualTopIndex = initialSize * initialSize;
        gridStates[virtualTopIndex] = true;
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

        gridStates[cellIndex] = true;

        if (row != 1 && isOpen(row - 1, column)) {
            union(cellIndex, getCellIndex(row - 1, column));
        } else if (row == 1) { // top row
            union(cellIndex, virtualTopIndex);
        }

        if (row != size && isOpen(row + 1, column)) {
            union(cellIndex, getCellIndex(row + 1, column));
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
        return gridStates[getCellIndex(row, column)];
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
        for (int column = 1; column <= size; column++) {

            if (gridUnions.connected(virtualTopIndex,
                    getCellIndex(size, column))) {
                return true;
            }
        }
        return false;
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
