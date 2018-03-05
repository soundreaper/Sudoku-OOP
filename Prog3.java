import java.util.Scanner;

public class Prog3 {

    public static String setPuzzleSpot(Sudoku sudoku) {
        int row,  col, value;

        Scanner userScanner = new Scanner(System.in);
        System.out.print("Enter row, column, and value to set (sep. by a space): ");
        row = userScanner.nextInt() - 1; // adjust for 0 through 8
        col = userScanner.nextInt() - 1; // adjust for 0 through 8
        value = userScanner.nextInt();

        return sudoku.setOneSpot(row, col, value);
    }

    public static String playSudoku(Sudoku sudoku) {
        System.out.println();
        String setPuzzleReturn = setPuzzleSpot(sudoku);
        System.out.println(setPuzzleReturn);

        if (setPuzzleReturn == "Number placed!") {
            boolean compareBool = sudoku.comparePuzzles();

            if (compareBool == true) {
                System.out.println("\nCongratulations! You solved the puzzle!");
                return "solved";
            }
        }

        boolean continueBool = wantsToContinue("Keep playing?");

        if (continueBool == false) {
            return "no continue";
        }

        return "continue";
    }

    public static boolean wantsToContinue(String prompt) {
        String answer;

        Scanner userScanner = new Scanner(System.in);
        System.out.print(prompt + " (y for yes): ");
        answer = userScanner.next();
        return answer.charAt(0)=='y' || answer.charAt(0)=='Y';
    } // end wantsToRepeat

    public static void main( String[] args ) {
        Sudoku sudoku = new Sudoku(false);
        sudoku.resetPuzzle(true);

        while (true) {
            System.out.println();
            sudoku.displayPuzzle();
            String playString = playSudoku(sudoku);

            if(playString == "continue") {
                continue;
            } else if(playString == "no continue") {
                break;
            } else if(playString == "solved") {
                boolean playAgain = wantsToContinue("Play Again?");

                if (playAgain == true) {
                    sudoku.resetPuzzle(true);
                    continue;
                } else if (playAgain == false) {
                    break;
                }
            }
        }
    }
}
