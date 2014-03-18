public class Board {

	private short[] blocks;
	private int size;

	public Board(int[][] blocks) {
		size = blocks.length;
		this.blocks = new short[size * size];
	}

	private short get(int row, int col) {
		return blocks[row * size + col];
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
	public Board twin() {

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

	public Iterable<Board> neighbors() {

	}

	public String toString() {
		StringBuilder str = new StringBuilder(size);
		str.append("\r\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				str.append(blocks[i][j]).append(" " );
			}
			str.append("\r\n");
		}

	}
}
