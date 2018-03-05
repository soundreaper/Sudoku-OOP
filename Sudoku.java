import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    public static final int SIZE = 9;

    private int[][] userPuzzle = new int[SIZE][SIZE];
    private int[][] puzzleAnswer = new int[SIZE][SIZE];

    public Sudoku(boolean isTrue) {
        this.userPuzzle = userPuzzle;
        this.puzzleAnswer = puzzleAnswer;

        fillPuzzle();
        copyPuzzle(puzzleAnswer, userPuzzle);
        setPuzzleHoles();
        comparePuzzles();

        if (isTrue == true) {
            System.out.println("\nFor debugging, the answer puzzle:");
            display2DimArray(puzzleAnswer);
        }
    }

    private void fillPuzzle() {
        puzzleAnswer[0][0] = 1; puzzleAnswer[0][1] = 2; puzzleAnswer[0][2] = 3;
        puzzleAnswer[0][3] = 4; puzzleAnswer[0][4] = 5; puzzleAnswer[0][5] = 6;
        puzzleAnswer[0][6] = 7; puzzleAnswer[0][7] = 8; puzzleAnswer[0][8] = 9;

        puzzleAnswer[1][0] = 4; puzzleAnswer[1][1] = 5; puzzleAnswer[1][2] = 6;
        puzzleAnswer[1][3] = 7; puzzleAnswer[1][4] = 8; puzzleAnswer[1][5] = 9;
        puzzleAnswer[1][6] = 1; puzzleAnswer[1][7] = 2; puzzleAnswer[1][8] = 3;

        puzzleAnswer[2][0] = 7; puzzleAnswer[2][1] = 8; puzzleAnswer[2][2] = 9;
        puzzleAnswer[2][3] = 1; puzzleAnswer[2][4] = 2; puzzleAnswer[2][5] = 3;
        puzzleAnswer[2][6] = 4; puzzleAnswer[2][7] = 5; puzzleAnswer[2][8] = 6;

        puzzleAnswer[3][0] = 2; puzzleAnswer[3][1] = 3; puzzleAnswer[3][2] = 4;
        puzzleAnswer[3][3] = 5; puzzleAnswer[3][4] = 6; puzzleAnswer[3][5] = 7;
        puzzleAnswer[3][6] = 8; puzzleAnswer[3][7] = 9; puzzleAnswer[3][8] = 1;

        puzzleAnswer[4][0] = 5; puzzleAnswer[4][1] = 6; puzzleAnswer[4][2] = 7;
        puzzleAnswer[4][3] = 8; puzzleAnswer[4][4] = 9; puzzleAnswer[4][5] = 1;
        puzzleAnswer[4][6] = 2; puzzleAnswer[4][7] = 3; puzzleAnswer[4][8] = 4;

        puzzleAnswer[5][0] = 8; puzzleAnswer[5][1] = 9; puzzleAnswer[5][2] = 1;
        puzzleAnswer[5][3] = 2; puzzleAnswer[5][4] = 3; puzzleAnswer[5][5] = 4;
        puzzleAnswer[5][6] = 5; puzzleAnswer[5][7] = 6; puzzleAnswer[5][8] = 7;

        puzzleAnswer[6][0] = 3; puzzleAnswer[6][1] = 4; puzzleAnswer[6][2] = 5;
        puzzleAnswer[6][3] = 6; puzzleAnswer[6][4] = 7; puzzleAnswer[6][5] = 8;
        puzzleAnswer[6][6] = 9; puzzleAnswer[6][7] = 1; puzzleAnswer[6][8] = 2;

        puzzleAnswer[7][0] = 6; puzzleAnswer[7][1] = 7; puzzleAnswer[7][2] = 8;
        puzzleAnswer[7][3] = 9; puzzleAnswer[7][4] = 1; puzzleAnswer[7][5] = 2;
        puzzleAnswer[7][6] = 3; puzzleAnswer[7][7] = 4; puzzleAnswer[7][8] = 5;

        puzzleAnswer[8][0] = 9; puzzleAnswer[8][1] = 1; puzzleAnswer[8][2] = 2;
        puzzleAnswer[8][3] = 3; puzzleAnswer[8][4] = 4; puzzleAnswer[8][5] = 5;
        puzzleAnswer[8][6] = 6; puzzleAnswer[8][7] = 7; puzzleAnswer[8][8] = 8;

        for (int currentNum = 1; currentNum <= 9; currentNum++) {
            int randInt;

            do {
                randInt = (int)((Math.random() * (9 - 1)) + 1);
            } while (randInt == currentNum);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (puzzleAnswer[i][j] == currentNum) {
                        puzzleAnswer[i][j] = randInt;
                    }
                    else if (puzzleAnswer[i][j] == randInt) {
                        puzzleAnswer[i][j] = currentNum;
                    }
                }
            }
        }

        int temp = 0;

        for (int i = 0; i < 7; i += 3) {
            int randRow = (int)((Math.random() * (2 - 1)) + 1);
            for (int j = 0; j < 9; j++) {
                temp = puzzleAnswer[i][j];

                puzzleAnswer[i][j] = puzzleAnswer[i+randRow][j];
                puzzleAnswer[i+randRow][j] = temp;
            }
        }

        for (int i = 0; i < 7; i += 3) {
            int randCol = (int)((Math.random() * (2 - 1)) + 1);
            for (int j = 0; j < 7; j+=3) {
                temp = puzzleAnswer[i][j];

                puzzleAnswer[i][j] = puzzleAnswer[i][j+randCol];
                puzzleAnswer[i][j+randCol] = temp;
            }
        }
    }

    private void copyPuzzle(int[][] array1, int[][] array2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                array2[i][j] = array1[i][j];
            }
        }
    }

    private void setPuzzleHoles() {
        int numHoles = 1; //Use this for a sudoku puzzle with only one hole to check if the compare method works quickly.
        //int numHoles = (int)((Math.random() * (36 - 25)) + 25);

        for (int k = 1; k <= numHoles; k++) {
            int randRow = (int)((Math.random() * (9 - 1)) + 1);
            int randCol = (int)((Math.random() * (9 - 1)) + 1);

            if (userPuzzle[randRow][randCol] != 0) {
                userPuzzle[randRow][randCol] = 0;
            }
        }
    }

    private void display2DimArray(int[][] puzzle) {
        System.out.println("\t|\t1\t2\t3\t|\t4\t5\t6\t|\t7\t8\t9\t");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + (i+1) + "  |\t");

            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) {
                    System.out.print(" " + "\t");
                }
                else {
                    System.out.print(puzzle[i][j] + "\t");
                }

                if (j == 2 || j == 5) {
                    System.out.print("|\t");
                }
            }

            if (i == 2 || i == 5) {
                System.out.print("\n- - - - - - - - - - - - - - - - - - - - - - - - - - -");
            }

            System.out.println();
        }
    }

    public boolean comparePuzzles() {
        if (Arrays.deepEquals(userPuzzle, puzzleAnswer) == true) {
            return true;
        }
        return false;
    }

    public void displayPuzzle() {
        display2DimArray(userPuzzle);
    }

    public void resetPuzzle(boolean isTrue) {
        fillPuzzle();
        copyPuzzle(puzzleAnswer, userPuzzle);
        setPuzzleHoles();

        if (isTrue == true) {
            System.out.println("\nFor debugging, the answer puzzle: ");
            display2DimArray(puzzleAnswer);
        }
    }

    public String setOneSpot(int row, int column, int value) {
        int minVal = 1, maxVal = userPuzzle.length;
        if ( row < 0 || row >= maxVal || column < 0 || column >= maxVal || value < minVal || value > maxVal) {
            return "Invalid input, must be "+minVal + " to " + maxVal + ", inclusive!";
        } else if( !isValidPlacement(row, column, value)) {
            return "You can't place that value there!" ;
        }

        // place the value
        userPuzzle[row][column] = value;
        return "Number placed!";
    }

    private boolean isValidPlacement(int row, int col, int value) {
        if( userPuzzle[row][col] != 0 ) // not empty?
            return false;
        // check if in the same row and column
        for( int i=0; i < userPuzzle.length; ++i ){
            if( userPuzzle[i][col] == value || userPuzzle[row][i] == value)
                return false;
        } // end checking same row and column

        // check if in its sub-square
        int begRow, begCol, endRow, endCol;
        begRow = 3* (row/3);
        endRow = begRow + 3;
        begCol = 3* (col/3);
        endCol = begCol + 3;
        for( int subrow = begRow; subrow < endRow ; ++subrow ){
            for( int subcol = begCol; subcol < endCol; ++subcol )
                if( userPuzzle[subrow][subcol] == value )
                    return false;
        } // end checking block
        return true;
    } // end isValidPlacement
}
