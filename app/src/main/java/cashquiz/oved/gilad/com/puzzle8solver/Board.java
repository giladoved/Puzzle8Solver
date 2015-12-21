package cashquiz.oved.gilad.com.puzzle8solver;

/**
 * Created by gilad on 12/19/15.
 */
import java.util.ArrayList;

//https://www.cs.princeton.edu/courses/archive/fall12/cos226/assignments/8puzzle.html
public class Board {

    private int [][] board;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        board = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    //board size N
    public int size() {
        return board.length;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int correctNum = (i * size()) + j + 1;
                if (board[i][j] == 0)
                    continue;
                if (board[i][j] != correctNum){
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] == 0)
                    continue;
                int dx = (board[i][j] - 1) / size();
                int dy = (board[i][j] - 1) % size();
                count += Math.abs(dx-i) + Math.abs(dy-j);
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal()  {
        return hamming() == 0 && manhattan() == 0;
    }

    // is the board solvable?
    public boolean isSolvable() {
        return false;
    }

    // is the board valid?
    public boolean isValid() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (getValue(i, j) < 1 || getValue(i, j) > (size()*size()-1))
                    return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Board y) {
        if (y.size() != size())
            return false;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != y.getValue(i, j))
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int r = 0;
        int c = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] == 0) {
                    r = i;
                    c = j;
                }
            }
        }

        Board left = new Board(board);
        if (c-1 >= 0) {
            swapValues(left, r, c, r, c-1);
            neighbors.add(left);
        }
        Board right = new Board(board);
        if (c+1 < size()) {
            swapValues(right, r, c, r, c+1);
            neighbors.add(right);
        }
        Board up = new Board(board);
        if (r-1 >= 0) {
            swapValues(up, r, c, r-1, c);
            neighbors.add(up);
        }
        Board down = new Board(board);
        if (r+1 < size()) {
            swapValues(down, r, c, r+1, c);
            neighbors.add(down);
        }

        return neighbors;
    }

    // get a board value at row i, col j
    public int getValue(int i, int j) {
        return board[i][j];
    }

    // set a board value at row i, col j to a
    public void setValue(int i, int j, int a) {
        board[i][j] = a;
    }

    public Board copy() {
        int n[][] = new int[size()][size()];
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                n[i][j] = getValue(i, j);
            }
        }
        return new Board(n);
    }

    // swap row i, col j with row i2, col j2 of board b
    public void swapValues(Board b, int i, int j, int i2, int j2) {
        int temp = b.getValue(i2, j2);
        b.setValue(i2, j2, b.getValue(i, j));
        b.setValue(i, j, temp);
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                str.append(board[i][j] + " ");
            }
            str.append('\n');
        }
        return str.toString();
    }

    public String boardWithBlank() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] == 0) {
                    str.append("   ");
                } else {
                    str.append(board[i][j] + " ");
                }
            }
            str.append('\n');
        }
        return str.toString();
    }

    //unit tests
    public static void main(String[] args) {
        int[][] test = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(test);
        System.out.println(board.toString());
        System.out.println("size: " + board.size());
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());
        for (Board b : board.neighbors()) {
            System.out.println("neighbor... ");
            System.out.println(b.toString());
        }
    }

}
