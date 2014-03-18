public class Percolation {
    private int size;
    private boolean[][] grid;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wqu2;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int n) {
        this.size = n;
        this.grid = new boolean[size][size];
        wqu = new WeightedQuickUnionUF(n * n + 2);
        wqu2 = new WeightedQuickUnionUF(n * n + 1);

        this.virtualBottom = n * n + 1;
        this.virtualTop = 0;
    }

    private void checkIndexes(int i, int j) {
        if (i <= 0 || i > size || j <= 0 || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int i, int j) {
        checkIndexes(i, j);
        grid[i - 1][j - 1] = true;
        int idx = convertToArrayIndex(i, j);

        //left
        if (j - 1 > 0 && isOpen(i, j - 1)) {
            int fromIdx = convertToArrayIndex(i, j - 1);
            wqu.union(fromIdx, idx);
            wqu2.union(fromIdx, idx);
        }
        //right
        if (j + 1 <= size && isOpen(i, j + 1)) {
            int fromIdx = convertToArrayIndex(i, j + 1);
            wqu.union(fromIdx, idx);
            wqu2.union(fromIdx, idx);
        }
        //top
        if (i - 1 > 0 && isOpen(i - 1, j)) {
            int fromIdx = convertToArrayIndex(i - 1, j);
            wqu.union(fromIdx, idx);
            wqu2.union(fromIdx, idx);
        }
        //bottom
        if (i + 1 <= size && isOpen(i + 1, j)) {
            int fromIdx = convertToArrayIndex(i + 1, j);
            wqu.union(fromIdx, idx);
            wqu2.union(fromIdx, idx);
        }

        if (i == 1) {
            wqu.union(virtualTop, idx);
            wqu2.union(virtualTop, idx);
        }
        if (i == size) {
            wqu.union(virtualBottom, idx);
        }

    }

    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        checkIndexes(i, j);
        return wqu2.connected(virtualTop, convertToArrayIndex(i, j)) && isOpen(i, j);
    }

    public boolean percolates() {
        return wqu.connected(virtualTop, virtualBottom);
    }

    private int convertToArrayIndex(int i, int j) {
        return (i - 1) * size + j;
    }



}
