public class Solver {

	private class Node {
		private int moves;
		private Board previousBoard;
		private Board currentBoard;

		private Node(Board board) {
			moves = 0;
			previousBoard = null;
			currentBoard = board;
		}
	}

	private MinPQ<Node> queue;


	public Solver(Board initial) {
		queue = new MinPQ<Node>();
		Node n = new Node(initial);
		queue.insert(n);
	}

	public boolean isSolvable() {
		return false;
	}

	public int moves() {
		return 0;
	}

	public Iterable<Board> solution() {
		return null;
	}

	public static void main(String[] args) {

	}
}