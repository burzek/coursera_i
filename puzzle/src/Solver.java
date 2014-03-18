import java.util.Iterator;
import java.util.List;

public class Solver {

	private class Node {
		private int moves;
		private Board previousBoard;
		private Board currentBoard;

		private Node(Board board, Board previousBoard, int moves) {
			this.moves = moves;
			this.previousBoard = previousBoard;
			this.currentBoard = board;
		}
	}

	private MinPQ<Node> queue;
	private List<Board> solution;


	public Solver(Board initial) {
		queue = new MinPQ<Node>();
		Node n = new Node(initial, null, 0);
		queue.insert(n);

		do {
			Node node = queue.delMin();
			solution.add(node.currentBoard);
			if (node.currentBoard.isGoal()) {
				break;
			}

			Iterator<Board> neighbors = node.currentBoard.neighbors().iterator();
			while (neighbors.hasNext()) {
				Board b = neighbors.next();
				if (b.equals(node.previousBoard)) {
					continue;	//optimization
				}
				queue.insert(new Node(b, node.currentBoard, node.moves + 1));
			}

		} while (!queue.isEmpty());
	}

	public boolean isSolvable() {
		return true;
	}

	public int moves() {
		return solution.size();
	}

	public Iterable<Board> solution() {
		return solution;
	}

	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				blocks[i][j] = in.readInt();
			}
		}


		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable()) {
			StdOut.println("No solution possible");
		} else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution()) {
				StdOut.println(board);
			}
		}
	}
}