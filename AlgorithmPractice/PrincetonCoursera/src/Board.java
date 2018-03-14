import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int[] blockArray;
    private final int[][] blocks;
    private final int dimension;
    private int moves = 0;

    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                list.add(blocks[i][j]);
            }
        }
        this.blockArray = new int[list.size()];
        for (int i = 0; i < blockArray.length; i++) {
            blockArray[i] = list.get(i);
        }
        this.dimension = blocks.length;
    }

    // board dimension n
    public int dimension() {
        return dimension;

    }

    // number of blocks out of place
    public int hamming() {
        int diff = 0;
        int hamming = 0;
        for (int i = 1; i <= 8; i++) {
            if (blockArray[i] != i) {
                diff++;
            }
        }
        hamming = this.moves + diff;
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {


    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean isGoal = false;
        for (int i = 1; i <= blockArray.length; i++) {
            if (i == blockArray[i - 1]) {
                isGoal = true;
            }
        }
        return isGoal;
    }


    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[] arrayOfTwinBoard = this.blockArray;
        int toSwap1 =StdRandom.uniform(0, 9);
        while (arrayOfTwinBoard[toSwap1] != 0){
            StdRandom.uniform(0, 9);
        }
        int toSwap2 =  StdRandom.uniform(0, 9);
        while (toSwap1 != toSwap2 && arrayOfTwinBoard[toSwap2] != 0) {
            StdRandom.uniform(0, 9);
        }
        int temp = arrayOfTwinBoard[toSwap1];
        arrayOfTwinBoard[toSwap1]= arrayOfTwinBoard[toSwap2];
        arrayOfTwinBoard[toSwap2] = temp;

        int [] [] twinBoard = new int[dimension()][dimension()];
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                twinBoard[row] [col] =  arrayOfTwinBoard[col + row * dimension()];
            }
        }
        return new Board(twinBoard);
    }
//
//    // does this board equal y?
//    public boolean equals(Object y) {
//    }
//
//    // all neighboring boards
//    public Iterable<Board> neighbors() {
//    }
//
//    // string representation of this board (in the output format specified below)
//    public String toString() {
//    }


    public int[] getBlockArray() {
        return blockArray;
    }

    // unit tests (not graded)

    public static void main(String[] args) {

        //testing the constructor
        int[][] blocks = new int[][]{
                {1, 8, 3},
                {4, 0, 6},
                {3, 8, 9}
        };

        Board board = new Board(blocks);
        for (int n : board.getBlockArray()) {
            System.out.println(n);
        }
        System.out.println(board.dimension);
    }
}


