import java.util.Scanner;

//might recode this with java's default GUI display libraries later

public class TicTacToe {

    //start with the creating the array for the board
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    private static String playerValue = "X";


    //now the main process  runs the game
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        boolean playAgain = false;
        int moveCount = 0;
        int player1Wins = 0; //X player
        int player2Wins = 0; //O player
        int ties = 0;


        do {
            // game code will go here, will loop so long as user indicates they want to play again

            startGame();
            //input move
            inputMove();

            playAgain = SafeInput.getYNConfirm(input, "Would you like to play another game? [Y/N]");

        } while(playAgain);

        input.close();

    }


    private static void startGame() {
        //contains the code for starting a game, called when the program starts or the user plays again
        clearBoard();
        System.out.println();
        showBoard();
        playerValue = "X";
    }

    private static void inputMove() {
    }

    private static boolean isValidMove(int row, int col) {
        if (board[row][col].equals(" ")) {
            return true;
        }
        else if (board[row][col].equals("X") || board[row][col].equals("O")) {
            return false;
        }
        else {
            System.out.println("Unexpected value in " + board[row][col] + "for isValidMove");
            return false;
        }
    }

    private static void togglePlayer() {
        if (playerValue.equals("X")) {
            playerValue = "O";
        }
        else if (playerValue.equals("O")) {
            playerValue = "X";
        }
        else {
            System.out.println("Unexpected player value for togglePlayer");
        }
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

    private static boolean isRowWin() {
        for(int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(playerValue) && board[r][1].equals(playerValue) && board[r][2].equals(playerValue)) {
                return true;
            }
        }
        return false; //now wins in any row
    }

    private static boolean isColWin() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(playerValue) && board[1][c].equals(playerValue) && board[2][c].equals(playerValue)) {
                return true;
            }
        }
        return false; //no wins in any column
    }

    private static boolean isDiagonalWin() {
        if (board[0][0].equals(playerValue) && board[1][1].equals(playerValue) && board[2][2].equals(playerValue)) {
            return true;
        }
        else if (board[0][2].equals(playerValue) && board[1][1].equals(playerValue) && board[2][0].equals(playerValue)) {
            return true;
        }
        else {
            return false;
        }
    }
}