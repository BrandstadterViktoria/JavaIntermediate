import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final int[] blockArray;
    private final int dimension;
    private final int[][] blocks;
    private int manhattan = 0;
    private int blankPositionRow = 0;
    private int blankPositionCol = 0;


    public Board(int[][] blocks) {
        this.blocks = blocks;
        List<Integer> list = new ArrayList<>();
        List<Integer> goalCoordinates = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                list.add(blocks[i][j]);
                goalCoordinates.add(i);
                goalCoordinates.add(j);
                if (blocks[i][j] == 0) {
                    this.blankPositionRow = i;
                    this.blankPositionCol = j;
                }
            }
        }
        this.blockArray = new int[list.size()];

        for (int i = 0; i < blockArray.length; i++) {
            blockArray[i] = list.get(i);
        }
        this.dimension = blocks.length;

        int row = -1;
        int col;
        int currentElementOfBlockArray = 1;
        int goalCoordinate = 0;
        for (int i = 1; currentElementOfBlockArray <= dimension * dimension - 1; i++) {
            if (blockArray[i - 1] == currentElementOfBlockArray) {
                currentElementOfBlockArray++;
                for (int k = 0; k <= i - 1; k += dimension) {
                    row++;
                }
                col = (i - 1) - (row * dimension);
                int currentManhattan = Math.abs(row - goalCoordinates.get(goalCoordinate)) + Math.abs(col - goalCoordinates.get(goalCoordinate + 1));
                goalCoordinate += 2;
                this.manhattan += currentManhattan;
                i = 0;
                row = -1;
            }
        }
    }

    public int dimension() {
        return dimension;

    }

    public int hamming() {
        int hamming = 0;
        for (int i = 1; i <= dimension - 1; i++) {
            if (blockArray[i] != i) {
                hamming++;
            }
        }
        return hamming;
    }


    public int manhattan() {
        return this.manhattan;
    }


    public boolean isGoal() {
        boolean isGoal = false;
        for (int i = 1; i < blockArray.length; i++) {
            if (i == blockArray[i - 1]) {
                isGoal = true;
                continue;
            } else {
                isGoal = false;
                break;
            }
        }
        return isGoal;
    }


    public Board twin() {
        int toSwap1 = StdRandom.uniform(1, 9);
        int toSwap2 = StdRandom.uniform(1, 9);
        while (toSwap1 == toSwap2) {
            toSwap2 = StdRandom.uniform(1, 9);
        }
        int[] arrayOfTwinBoard = swap(toSwap1, toSwap2);
        return newBoardFrom1DArray(arrayOfTwinBoard);
    }


    public boolean equals(Board board) {
        boolean equals = false;
        if (Arrays.equals(board.getBlockArray(), this.getBlockArray())) {
            equals = true;
        }

        if (board.getClass() != this.getClass()) {
            equals = false;
        }

        return equals;
    }

    public Iterable<Board> neighbors() {
        int blankIndex = blankPositionCol + blankPositionRow * dimension;
        Stack<Board> neighbors = new Stack<>();
        if (blankPositionRow != dimension - 1 && blankPositionRow >= 0) {
            neighbors.push(newBoardFrom1DArray(swap(blankIndex, blankIndex + dimension)));
        }
        if (blankPositionRow != 0 && blankPositionRow <= dimension - 1) {
            neighbors.push(newBoardFrom1DArray(swap(blankIndex, blankIndex - dimension)));
        }
        if (blankPositionCol != dimension - 1 && blankPositionCol >= 0) {
            neighbors.push(newBoardFrom1DArray(swap(blankIndex, blankIndex + 1)));
        }
        if (blankPositionCol != 0 && blankPositionCol <= dimension - 1) {
            neighbors.push(newBoardFrom1DArray(swap(blankIndex, blankIndex - 1)));
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    public int[] getBlockArray() {
        return blockArray;
    }

    private int[] swap(int toSwap, int goalIndexForSwap) {
        int[] swapped = this.blockArray.clone();
        int temp = swapped[goalIndexForSwap];
        swapped[goalIndexForSwap] = swapped[toSwap];
        swapped[toSwap] = temp;
        return swapped;
    }


    private Board newBoardFrom1DArray(int[] arrayToConvert) {
        int[][] newBoard = new int[dimension()][dimension()];
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                newBoard[row][col] = arrayToConvert[col + row * dimension()];
            }
        }
        return new Board(newBoard);
    }

    public static void main(String[] args) {

    }
}


