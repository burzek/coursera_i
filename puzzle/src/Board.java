import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int[] blocks;
    private int size;

    public Board(int[][] blocks) {
        size = blocks.length;
        this.blocks = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.blocks[i * size + j] = blocks[i][j];
            }
        }
    }


    public int dimension() {
        return size;
    }

    public int hamming() {
        int hamming = 0;
        for (int pos = 0; pos < size * size; pos++) {
            if (blocks[pos] != 0 && blocks[pos] != pos + 1) {
                hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int pos = 0; pos < size * size; pos++) {
			if (blocks[pos] == 0) {
				continue;
			}

			int posNeeded = blocks[pos] - 1;
			if (posNeeded != pos) {
				int col = pos % size;
				int row = pos / size;

				int colNeeded = posNeeded % size;
				int rowNeeded = posNeeded / size;

				int diff = col - colNeeded;
				if (diff < 0) {
					diff = -diff;
				}

				manhattan += diff;

				diff = row - rowNeeded;
				if (diff < 0) {
					diff = -diff;
				}

				manhattan += diff;

			}
		}
        return  manhattan;
    }

    public boolean isGoal() {
        for (int i = 0; i < size * size - 1; i++) {
            if (blocks[i] != i + 1) {
                return false;
            }
        }
        return true;
    }


    private int[][] convertBoard(int[] blocksParam) {
        int[][] newBlocks = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newBlocks[i][j] = blocksParam[i * size + j];
            }
        }
        return newBlocks;
    }

    public Board twin() {
        int[] twin = Arrays.copyOf(blocks, size * size);
        int row = 0;
        if (twin[row * size] == 0 || twin[row * size + 1] == 0) {
            row++;
        }

        int tmp = this.blocks[row * size];
        twin[row * size] = twin[row * size + 1];
        twin[row * size + 1] = tmp;

        return new Board(convertBoard(twin));

    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (!(y instanceof  Board)) {
            return false;
        }

        Board other = (Board) y;
        for (int i = 0; i < size * size; i++) {
            if (blocks[i] != other.blocks[i]) {
                return false;
            }
        }

        return true;
    }

    private int[] swapEmpty(int[] blocksParam, int row0, int col0, int rowTo, int colTo) {
        int[] swapped = Arrays.copyOf(blocksParam, size * size);
        int idxFrom = row0 * size + col0;
        int idxTo = rowTo * size + colTo;
        swapped[idxFrom] = swapped[idxTo];
        swapped[idxTo] = 0;
        return swapped;
    }

    public Iterable<Board> neighbors() {
        List<Board> boards = new ArrayList<Board>();

        int pos = 0;
        while (this.blocks[pos] != 0) {
            pos++;
        }
        int row0 = pos / size;
        int col0 = pos % size;

        //left
        if (col0 != 0) {
            int[] swapBlocks = swapEmpty(this.blocks, row0, col0, row0, col0 - 1);
            boards.add(new Board(convertBoard(swapBlocks)));

        }
        //right
        if (col0 != size - 1) {
            int[] swapBlocks = swapEmpty(this.blocks, row0, col0, row0, col0 + 1);
            boards.add(new Board(convertBoard(swapBlocks)));
        }
        //up
        if (row0 != 0) {
            int[] swapBlocks = swapEmpty(this.blocks, row0, col0, row0 - 1, col0);
            boards.add(new Board(convertBoard(swapBlocks)));
        }
        //down
        if (row0 != size - 1) {
            int[] swapBlocks = swapEmpty(this.blocks, row0, col0, row0 + 1, col0);
            boards.add(new Board(convertBoard(swapBlocks)));
        }


        return boards;


    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", this.blocks[i * size + j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
