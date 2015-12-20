package cashquiz.oved.gilad.com.puzzle8solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by gilad on 12/19/15.
 */
public class Solver {

    private Board initialState;
    int minMoves = Integer.MAX_VALUE;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        initialState = initial;
    }

    // min number of moves to solve initial board
    public int moves() {
        return minMoves;
    }

    // sequence of boards in a shortest solution
    public ArrayList<Board> solution() {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashMap<Board, Node> frontierHash = new HashMap<Board, Node>();
        HashSet<Board> explored = new HashSet<Board>();
        ArrayList<Board> path = new ArrayList<Board>();

        frontier.add(new Node(initialState, null));
        while (true) {
            if (frontier.isEmpty()) {
                System.out.println("No Solution!!");
                return null;
            }

            Node n = frontier.poll();
            Board h = n.node;
            if (h.isGoal()) {
                ArrayList<Node> nodes = new ArrayList<Node>();
                Node currNode = n;
                while (currNode != null) {
                    nodes.add(currNode);
                    currNode = frontierHash.get(currNode.parent);
                }
                nodes.add(new Node(initialState, null));
                Collections.reverse(nodes);

                for (Node node : nodes) {
                    path.add(node.node);
                }

                return path;
            }

            for (Board a : h.neighbors()) {
                if (explored.contains(a) || frontier.contains(a)) {
                    continue;
                }
                Node node = new Node(a, h);
                frontier.add(node);
                frontierHash.put(a, node);
            }
            explored.add(h);
        }
    }

    public static void main(String[] args) {
        int[][] i = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        Board initial = new Board(i);
        Solver solver = new Solver(initial);
        for (Board board : solver.solution())
            System.out.println(board);
    }

}

class Node implements Comparator<Node>, Comparable<Node> {
    public Board node;
    public Board parent;

    public Node(Board n, Board p) {
        node = n;
        parent = p;
    }

    @Override
    public int compare(Node b1, Node b2) {
        return b2.node.manhattan() - b1.node.manhattan();
    }

    @Override
    public int compareTo(Node b) {
        return node.manhattan() - b.node.manhattan();
    }
}
