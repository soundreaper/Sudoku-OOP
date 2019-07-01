// Play Sudoku (OOP)
// Subal Pant
// 02/14/2018
// System: Mac OS X - High Sierra, Compiler: IntelliJ IDEA

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

// Output:
/*

Invalid Input:
(Out of range row)
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	8	6	4	|	5	7	3	|	2	1	9
 2  |	1	2	9	|	6	8	4	|	7	5	3
 3  |	7	5	3	|	1	2	9	|	6	8	4
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	4	8	7	|	3	 	1	|	9	2	6
 5  |	2	9	6	|	8	4	7	|	5	3	1
 6  |	5	3	1	|	2	9	6	|	8	4	7
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	7	4	5	|	1	3	2	|	6	9	8
 8  |	9	6	8	|	4	7	5	|	3	1	2
 9  |	3	1	2	|	9	6	8	|	4	7	5

Enter row, column, and value to set (sep. by a space): 11 1 8
Invalid input, must be 1 to 9, inclusive!
Keep playing? (y for yes): y

(Out of range column)
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	9	5	3	|	2	6	8	|	4	7	1
 2  |	7	4	1	|	5	9	3	|	6	2	8
 3  |	6	2	8	|	7	4	1	|	5	9	3
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	3	9	6	|	8	2	7	|	1	4	5
 5  |	4	1	5	|	9	3	 	|	2	8	7
 6  |	2	8	7	|	4	1	5	|	9	3	6
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	6	3	2	|	7	8	4	|	5	1	9
 8  |	1	5	9	|	3	6	2	|	8	7	4
 9  |	8	7	4	|	1	5	9	|	3	6	2

Enter row, column, and value to set (sep. by a space): 1 12 8
Invalid input, must be 1 to 9, inclusive!
Keep playing? (y for yes): y

(Out of range value)
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	8	1	2	|	4	9	6	|	3	5	7
 2  |	5	3	7	|	1	8	2	|	9	4	6
 3  |	9	4	6	|	5	3	 	|	1	8	2
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	2	8	9	|	6	4	5	|	7	3	1
 5  |	3	7	1	|	8	2	9	|	4	6	5
 6  |	4	6	5	|	3	7	1	|	8	2	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	9	2	4	|	5	6	3	|	1	7	8
 8  |	7	1	8	|	2	9	4	|	6	5	3
 9  |	6	5	3	|	7	1	8	|	2	9	4

Enter row, column, and value to set (sep. by a space): 1 2 18
Invalid input, must be 1 to 9, inclusive!
Keep playing? (y for yes): y

(Value Already in Spot)
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	2	6	4	|	8	9	3	|	1	5	7
 2  |	5	1	7	|	6	2	4	|	9	8	3
 3  |	9	8	3	|	5	1	7	|	6	2	4
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	4	2	9	|	3	8	5	|	7	1	6
 5  |	1	7	6	|	2	4	9	|	8	3	5
 6  |	8	 	5	|	1	7	6	|	2	4	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	9	4	8	|	5	3	1	|	6	7	2
 8  |	7	6	2	|	4	9	8	|	3	5	1
 9  |	3	5	1	|	7	6	2	|	4	9	8

Enter row, column, and value to set (sep. by a space): 1 1 2
You can't place that value there!
Keep playing? (y for yes):



Valid Input:
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	7	9	5	|	8	4	1	|	6	3	2
 2  |	3	6	2	|	9	7	5	|	4	8	1
 3  |	4	8	1	|	3	6	2	|	9	7	5
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	5	7	4	|	1	8	3	|	2	6	9
 5  |	6	2	9	|	7	5	4	|	8	1	3
 6  |	8	1	3	|	6	2	9	|	7	5	4
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	4	5	8	|	3	1	6	|	9	 	7
 8  |	2	9	7	|	5	4	8	|	1	3	6
 9  |	1	3	6	|	2	9	7	|	5	4	8

Enter row, column, and value to set (sep. by a space): 7 8 2
Number placed!

	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	9	3	7	|	8	1	6	|	5	4	2
 2  |	4	5	2	|	3	9	7	|	1	8	6
 3  |	1	8	6	|	4	5	2	|	3	9	7
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	7	9	1	|	6	8	4	|	2	5	3
 5  |	5	2	3	|	9	7	 	|	8	6	4
 6  |	8	6	4	|	5	2	3	|	9	7	1
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	1	7	8	|	4	6	5	|	3	2	9
 8  |	2	3	9	|	7	1	8	|	6	4	5
 9  |	6	4	5	|	2	3	9	|	7	1	8

Enter row, column, and value to set (sep. by a space): 5 6 1
Number placed!

	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	9	1	3	|	6	4	7	|	5	2	8
 2  |	2	5	8	|	1	9	3	|	4	6	7
 3  |	4	6	7	|	2	5	8	|	1	9	3
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	3	9	4	|	7	6	2	|	8	5	1
 5  |	5	8	1	|	9	3	 	|	6	7	2
 6  |	6	7	2	|	5	8	1	|	9	3	4
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	4	3	6	|	2	7	5	|	1	8	9
 8  |	8	1	9	|	3	4	6	|	7	2	5
 9  |	7	2	5	|	8	1	9	|	3	4	6

Enter row, column, and value to set (sep. by a space): 5 6 4
Number placed!



User Quitting Before Solving:
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	6	2	9	|	4	1	7	|	8	3	5
 2  |	3	8	5	|	2	6	9	|	1	4	7
 3  |	1	 	7	|	3	8	5	|	2	6	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	9	6	1	|	7	4	3	|	5	8	2
 5  |	8	5	2	|	6	9	1	|	4	7	3
 6  |	4	7	3	|	8	5	 	|	6	9	1
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	1	9	4	|	3	7	8	|	2	5	6
 8  |	5	2	6	|	9	1	4	|	7	3	8
 9  |	7	3	8	|	5	2	6	|	9	1	4

Enter row, column, and value to set (sep. by a space): 6 6 2
Number placed!
Keep playing? (y for yes): n

Process finished with exit code 0


User Solving:
	|	1	2	3	|	4	5	6	|	7	8	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 1  |	1	6	9	|	3	4	8	|	2	7	5
 2  |	7	2	5	|	6	1	9	|	4	3	8
 3  |	4	3	8	|	7	2	5	|	6	1	9
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 4  |	9	1	4	|	8	3	7	|	5	2	6
 5  |	2	5	6	|	1	9	4	|	3	8	7
 6  |	3	8	7	|	2	5	6	|	1	 	4
- - - - - - - - - - - - - - - - - - - - - - - - - - -
 7  |	4	9	3	|	7	8	2	|	6	5	1
 8  |	5	6	1	|	9	4	3	|	8	7	2
 9  |	8	7	2	|	5	6	1	|	9	4	3

Enter row, column, and value to set (sep. by a space): 6 8 9
Number placed!

Congratulations! You solved the puzzle!
Play Again? (y for yes): n

Process finished with exit code 0

 */
