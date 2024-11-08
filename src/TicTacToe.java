import java.util.Scanner;

//might recode this with java's default GUI display libraries later

public class TicTacToe {

    //start with the creating the array for the board
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];


    //now the main process  runs the game
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        boolean playAgain = false;

        //define players
        String playerX = "X";
        String playerO = "O";


        do {
            // game code will go here, will loop so long as user indicates they want to play again

            startGame();

            playAgain = SafeInput.getYNConfirm(input, "Would you like to play another game? [Y/N]");

        } while(playAgain);

        input.close();

    }

    private static void startGame() {
        //contains the code for starting a game, called when the program starts or the user plays again
        clearBoard();
        System.out.println();
        showBoard();
    }

    private static void showBoard() {
        //prints the board using the current board array values and adds separators
        /*manually coding with println instead of using for loops because it's more legible, and the outer borders are
        different from the interior separators. Less work to just make everything loop-less instead of mixing in
        for loops when there are so few rows and columns*/

        System.out.println("|---|---|---|"); //prints top border
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |"); //first row
        System.out.println("|---+---+---|"); //divider
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |"); //second row
        System.out.println("|---+---+---|"); //divider
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |"); //third row
        System.out.println("|---|---|---|"); //bottom border

    }

    private static void clearBoard() {
        //loops through every cell on the board and sets to blank
        for(int r=0; r < ROWS; r++) {
            for(int c=0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }


    }
}