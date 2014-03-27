import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solver {

    private class Node implements Comparable {
        private int moves;
        private Node previousNode;
        private Board currentBoard;

        private Node(Board board, Node previousNode, int moves) {
            this.moves = moves;
            this.previousNode = previousNode;
            this.currentBoard = board;
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            return (this.currentBoard.manhattan() + moves) - (other.currentBoard.manhattan() + other.moves);
        }
    }

    private MinPQ<Node> queue;
    private MinPQ<Node> queueTwin;
    private boolean isSolvable;

    private List<Board> solutionList;

    public Solver(Board initial) {
        Node solutionNode = null;

        queue = new MinPQ<Node>();
        queueTwin = new MinPQ<Node>();

        queue.insert(new Node(initial, null, 0));
        queueTwin.insert(new Node(initial.twin(), null, 0));

        while (!isSolvable) {
            Node node = queue.delMin();
            Node nodeTwin = queueTwin.delMin();

            if (nodeTwin.currentBoard.isGoal()) {
                break;
            }

            if (node.currentBoard.isGoal()) {
                solutionNode = node;
                isSolvable = true;
            }

            processNeighbors(queue, node);
            processNeighbors(queueTwin, nodeTwin);

        }

        //
        solutionList = new LinkedList<Board>();
        if (isSolvable) {
            while (solutionNode != null) {
                solutionList.add(0, solutionNode.currentBoard);
                solutionNode = solutionNode.previousNode;
            }
        }

    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return solutionList.size() - 1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable) {
			return null;
		} else {
			return solutionList;
		}
    }


    private void processNeighbors(MinPQ<Node> queueParam, Node node) {
        Iterator<Board> neighbors = node.currentBoard.neighbors().iterator();
        while (neighbors.hasNext()) {
            Board b = neighbors.next();
            if (node.previousNode != null && b.equals(node.previousNode.currentBoard)) {
                continue;	//optimization
            }
            queueParam.insert(new Node(b, node, node.moves + 1));
        }
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