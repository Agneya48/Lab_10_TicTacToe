import java.util.Arrays;
import java.util.Scanner;

//might recode this with java's default GUI display libraries later.
/*Also, if the lab wasn't partially meant to practice 2D arrays, I would have just labeled the squares 1-9
so players could input their choice more easily
 */

public class TicTacToe {

    //start with the creating the array for the board
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    private static String startingPlayer = "X";
    private static String playerValue = "X";


    //now the main process  runs the game
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean playAgain = false;
        int moveCount = 0;
        int xWins = 0; //X player
        int oWins = 0; //O player
        int ties = 0;
        boolean togglePrompt = false;
        boolean gameDone = false;

        //print greeting and instructions on opening program
        System.out.println("\nWelcome to simple 3x3 Tic Tac Toe");
        System.out.println("\nX moves first");
        System.out.println("Enter moves by coordinates of the row and column");
        System.out.println("1 for left/top, 2 for middle, 3 for right/bottom");

        do {// loops as long as user indicates they want to play again

            if (playAgain) //will only trigger if the player(s) decided to play another game after finishing one
                //lets players swap default starting character
                togglePrompt = SafeInput.getYNConfirm(input, "Swap order of players? [Y/N]");
            if (togglePrompt)
                if (startingPlayer.equals("X"))
                    startingPlayer = "O";
                else if (startingPlayer.equals("O"))
                    startingPlayer = "X";
                else
                    System.out.println("Unexpected player value line 48");

            startGame();//clears board and resets all relevant game values to 0
            gameDone = false;
            moveCount = 0;


            do {// loops through turns until a winner or tie is determined

                System.out.println("\n" + playerValue + " move");
                playerInput(input); //inputs a move from a player, stores in move
                moveCount++;
                System.out.println("\nTurn " + moveCount);
                showBoard();

                if (moveCount >= 5) { //check for possible wins
                    if (isWin()) { //found a win, increments win value and displays winner
                        System.out.println("\n" + playerValue + " Wins!");
                        gameDone = true;
                        if (playerValue.equals("X")) xWins++;
                        else if (playerValue.equals("O")) oWins++;
                        else System.out.println("Unexpected playerValue line 57");
                    }
                }
                if (moveCount >= 7) {//check for ties, only inevitable after turn 7
                    if (isTie()) {
                        System.out.println("\nThe game ends in a tie.");
                        ties++;
                        gameDone = true;
                    }
                }

                togglePlayer(); //turn ends, switch between players. Has no effect if game ends

            } while (!gameDone);

            //after finishing a game, print total wins and ties
            System.out.println("X Wins: " + xWins);
            System.out.println("O Wins: " + oWins);
            System.out.println("Ties: " + ties);

            playAgain = SafeInput.getYNConfirm(input, "\nWould you like to play another game? [Y/N]");

        } while(playAgain);

        input.close();

    }


    private static void startGame() {
        //contains the code for starting a game, called when the program starts or the user plays again
        clearBoard();
        System.out.println();
        showBoard();
        playerValue = startingPlayer;
    }

    private static void playerInput(Scanner pipe) {
    /* takes Scanner and move array declared in main, prompts player for input, and enters chosen move into the
     array in form (row, column). Then enters move into square if it is empty.*/
        boolean valid = false;
        do { //loops until a blank square is inputted
            int row = SafeInput.getRangedInt(pipe, "Row", 1, 3); //row value
            int col = SafeInput.getRangedInt(pipe, "Column", 1, 3); //column value
            if (isValidMove(row-1, col-1)) {
                valid = true;
                board[row-1][col-1] = playerValue;
            }
            else {
                System.out.println("Invalid move. Square already filled");
            }
        } while (!valid);
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
        /*manually coding with println instead of using for loops because it's more legible, and easier to change
        Originally, the outer borders and interior separators were different. Less work to just make everything
        loop-less instead of mixing in for loops when there are so few rows and columns*/

        System.out.println("|---|---|---|"); //prints top border
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |"); //first row
        System.out.println("|---|---|---|"); //divider
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |"); //second row
        System.out.println("|---|---|---|"); //divider
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

    private static boolean isWin() {
        //checks for row, column and diagonal win conditions, returns true if found
        if (isRowWin() || isColWin() || isDiagonalWin()) return true;
        else return false;
    }

    private static boolean isRowWin() {
        for(int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(playerValue) && board[r][1].equals(playerValue) && board[r][2].equals(playerValue)) {
                return true;
            }
        }
        return false; //no wins in any row
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

    private static boolean vectorContainsX (String[] vector) {
        if (Arrays.stream(vector).anyMatch("X"::equals))
            return true;
        else return false;
    }

    private static boolean vectorContainsO (String[] vector) {
        if (Arrays.stream(vector).anyMatch("O"::equals))
            return true;
        else return false;
    }

    private static boolean isTie() { //checks for ties by brute-force all vectors
        /*could definitely be done more efficiently (like keeping the list of poss win vectors higher scope in class
         and removing them as they became invalid, but efficiency doesn't matter for a tiny game like this*/
        String[] vector1 = {board[0][0], board[0][1], board[0][2]};
        String[] vector2 = {board[1][0], board[1][1], board[1][2]};
        String[] vector3 = {board[2][0], board[2][1], board[2][2]};
        String[] vector4 = {board[0][0], board[1][0], board[2][0]};
        String[] vector5 = {board[0][1], board[1][1], board[2][1]};
        String[] vector6 = {board[0][2], board[1][2], board[2][2]};
        String[] vector7 = {board[0][0], board[1][1], board[2][2]};
        String[] vector8 = {board[0][2], board[1][1], board[2][0]};

        /*would ideally just create a list of arrays and iterate through a for loop, but haven't figured
        out the syntax for that yet so we get this nonsense instead*/

        if ((vectorContainsX(vector1) && vectorContainsO(vector1))
                && (vectorContainsX(vector2) && vectorContainsO(vector2))
                && (vectorContainsX(vector3) && vectorContainsO(vector3))
                && (vectorContainsX(vector4) && vectorContainsO(vector4))
                && (vectorContainsX(vector5) && vectorContainsO(vector5))
                && (vectorContainsX(vector6) && vectorContainsO(vector6))
                && (vectorContainsX(vector7) && vectorContainsO(vector7))
                && (vectorContainsX(vector8) && vectorContainsO(vector8))) {
            return true;
        }
        else return false;
        }
}