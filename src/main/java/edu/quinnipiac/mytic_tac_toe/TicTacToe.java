package edu.quinnipiac.mytic_tac_toe;
/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @date 1/5/2017
 *
 * @Secondauthor Jenna Saleh
 */
public class TicTacToe implements ITicTacToe {

    // The game board and the game status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] gameBoard = new int[ROWS][COLS]; // game board in 2D array
    private static int COUNT;
    /**
     * COMPUTER = "  O  " = NOUGHT = 2
     * PLAYER   = "  X  " = CROSS = 1
     */

    /**
     * clear board and set current player
     */
    public TicTacToe(){
        COUNT=0;
    }
    @Override
    public void clearBoard() {

        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                gameBoard[row][col] = EMPTY;
            }
        }
    }

    @Override
    public void setMove(int player, int location) {
        gameBoard[location/3][location % 3] =player;
        COUNT++;
    }

    @Override
    public int getComputerMove() {
        int CompMove = (int) (Math.random()*9);
        while (gameBoard[CompMove/3][CompMove %3]!=EMPTY) {
            CompMove =(int) (Math.random()*9);
        }
        return CompMove;
    }

    @Override
    public int checkForWinner() {
        for (int row = 0; row < 3; row++) {
            if ((gameBoard[row][0] == gameBoard[row][1]) && (gameBoard[row][1] == gameBoard[row][2])) {
                if (gameBoard[row][0] == CROSS) {
                    return CROSS_WON;
                } else if (gameBoard[row][0] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        for (int row = 0; row < 3; row++) {
            if ((gameBoard[0][row] == gameBoard[1][row]) && (gameBoard[1][row] == gameBoard[2][row])) {
                if (gameBoard[0][row] == CROSS) {
                    return CROSS_WON;
                } else if (gameBoard[0][row] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        for (int row = 0; row < 3; row = row + 2) {
            if ((gameBoard[0][row] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][2 - row])) {
                if (gameBoard[0][row] == CROSS) {
                    return CROSS_WON;
                } else if (gameBoard[0][row] == NOUGHT) {
                    return NOUGHT_WON;
                }
            }
        }

        if (COUNT == 8){
            return TIE;
        }

        return PLAYING;
    }
    /**
     *  Print the game board
     */
    public  void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(gameBoard[row][col]); // print each of the cells
                if (col != COLS - 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("-----------"); // print horizontal partition
            }
        }
        System.out.println();
    }

    /**
     * Print a cell with the specified "content"
     * @param content either CROSS, NOUGHT or EMPTY
     */
    public  void printCell(int content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case NOUGHT: System.out.print(" O "); break;
            case CROSS:  System.out.print(" X "); break;
        }
    }

}
