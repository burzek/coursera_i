import java.util.ArrayList;
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
		for (int pos = 0; pos < size * size - 1; pos++) {
			hamming += (blocks[pos] != pos + 1 ? 1 : 0);
		}
		return hamming + 0;	//todo
	}

	public int manhattan() {
		int manhattan = 0;
		for (int pos = 0; pos < size * size - 1; pos++) {
			int diff = blocks[pos] - (pos + 1);
			diff = diff < 0 ? -diff : diff;
			manhattan += ((diff / size) + (diff % size));
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


	private int[][] convertBoard() {
		int[][] newBlocks = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newBlocks[i][j] = this.blocks[i * size + j];
			}
		}
		return  newBlocks;
	}

	public Board twin() {
		int[][] newBlocks = convertBoard();

		int row = StdRandom.uniform(size);
		int fromCol = 0;
		if (newBlocks[row][fromCol] == 0) {
			fromCol++;
		}
		int toCol = fromCol + 1;
		if (newBlocks[row][toCol] == 0) {
			toCol++;
		}
		int tmp = newBlocks[row][fromCol];
		newBlocks[row][fromCol] = newBlocks[row][toCol];
		newBlocks[row][toCol] = tmp;

		return new Board(newBlocks);

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

	private int[][] swapEmpty(int[][] blocks, int row0, int col0, int rowTo, int colTo) {
		blocks[row0][col0] = blocks[rowTo][colTo];
		blocks[rowTo][colTo] = 0;
		return blocks;
	}

	public Iterable<Board> neighbors() {
		List<Board> boards = new ArrayList<Board>();

		int[][] blocks = convertBoard();
		int pos = 0;
		while (this.blocks[pos] != 0) {
			pos++;
		}
		int row0 = pos / size;
		int col0 = pos % size;

		//left
		if (col0 != 0) {
			int[][] swapBlocks = swapEmpty(blocks, row0, col0, row0, col0 - 1);
			boards.add(new Board(swapBlocks));

		}
		//right
		if (col0 != size - 1) {
			int[][] swapBlocks = swapEmpty(blocks, row0, col0, row0, col0 + 1);
			boards.add(new Board(swapBlocks));
		}
		//up
		if (row0 != 0) {
			int[][] swapBlocks = swapEmpty(blocks, row0, col0, row0 - 1, col0);
			boards.add(new Board(swapBlocks));
		}
		//down
		if (row0 != size - 1) {
			int[][] swapBlocks = swapEmpty(blocks, row0, col0, row0 + 1, col0);
			boards.add(new Board(swapBlocks));
		}


		return boards;


	}

	public String toString() {
		StringBuilder str = new StringBuilder(size);
		str.append("\r\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				str.append(blocks[i * size + j]).append(" " );
			}
			str.append("\r\n");
		}
		return str.toString();
	}
}
