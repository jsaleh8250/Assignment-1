package edu.quinnipiac.mytic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends Activity implements ITicTacToe{

    public static int HUMAN_PLAYER = 1, COMPUTER_PLAYER = 2;

    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] gameBoard = new int[ROWS][COLS]; // game board in 2D array
    private static int COUNT;
    int currentState = ITicTacToe.PLAYING; // sets the state to playing based on the interface
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearBoard();
        name = getIntent().getStringExtra("name");
        TextView playerName = findViewById(R.id.Game_Text);
        playerName.setText("Time for Tic Tac Toe " + name);
    }

    public void playerMove(View view){
        int location = idToLocation(view.getId()); // converts button id to an input-able location

        if (currentState == ITicTacToe.PLAYING) { // if computer has won, skip
            setMove(HUMAN_PLAYER, location); // player turn
            winCheck();
            if (currentState == ITicTacToe.PLAYING) {
                setMove(COMPUTER_PLAYER, getComputerMove());
                winCheck();
            }
        }
    }

    public void winCheck(){
        currentState = checkForWinner();
        if (currentState == ITicTacToe.CROSS_WON) {
            Toast.makeText(getApplicationContext(), "'X' won!", Toast.LENGTH_LONG).show(); // toast showing x won
            returnToMenu();
        } else if (currentState == ITicTacToe.NOUGHT_WON) {
            Toast.makeText(getApplicationContext(), "'O' won!", Toast.LENGTH_LONG).show(); // toast showing o won
            returnToMenu();
        } else if (currentState == ITicTacToe.TIE) {
            Toast.makeText(getApplicationContext(), "It's a TIE!", Toast.LENGTH_LONG).show(); // toast showing tie
            returnToMenu();
        }
    }

    public void returnToMenu(){
        Intent intent = new Intent(this,SimpleTitleScreen.class);
        startActivity(intent); // returns to main menu
    }
    public void onReset(View view){
        clearBoard();
    }
    // takes the button id and converts it into a location
    public int idToLocation(int id){
        switch(id){
            case R.id.button_00:
                return 0;
            case R.id.button_01:
                return 1;
            case R.id.button_02:
                return 2;
            case R.id.button_10:
                return 3;
            case R.id.button_11:
                return 4;
            case R.id.button_12:
                return 5;
            case R.id.button_20:
                return 6;
            case R.id.button_21:
                return 7;
            case R.id.button_22:
                return 8;
        }
        return 0;
    }
    // changes button image at a specific location
    public void display(int player, int location){
        switch(location){
            case 0:
                findViewById(R.id.button_00).setBackgroundResource(cell(player));
                break;
            case 1:
                findViewById(R.id.button_01).setBackgroundResource(cell(player));
                break;
            case 2:
                findViewById(R.id.button_02).setBackgroundResource(cell(player));
                break;
            case 3:
                findViewById(R.id.button_10).setBackgroundResource(cell(player));
                break;
            case 4:
                findViewById(R.id.button_11).setBackgroundResource(cell(player));
                break;
            case 5:
                findViewById(R.id.button_12).setBackgroundResource(cell(player));
                break;
            case 6:
                findViewById(R.id.button_20).setBackgroundResource(cell(player));
                break;
            case 7:
                findViewById(R.id.button_21).setBackgroundResource(cell(player));
                break;
            case 8:
                findViewById(R.id.button_22).setBackgroundResource(cell(player));
                break;
        }
    }
//Gets buttons back to blank
    @Override
    public void clearBoard() {
        findViewById(R.id.button_00).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_01).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_02).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_10).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_11).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_12).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_20).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_21).setBackgroundResource(R.drawable.blankspace);
        findViewById(R.id.button_22).setBackgroundResource(R.drawable.blankspace);

        // sets all space back to empty
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                gameBoard[row][col] = EMPTY;
            }
        }
        COUNT = 0;
    }
    @Override
    public void setMove(int player, int location) {
        gameBoard[location/3][location % 3] =player;
        COUNT++;
        display(player,location); //Displays X
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

    //Puts the X and O images in the buttons
    public int cell(int content) {
        switch (content) {
            case EMPTY:
                return R.drawable.blankspace;
            case NOUGHT:
                return R.drawable.nought;
            case CROSS:
                return R.drawable.cross;
        }
        return R.drawable.blankspace;
    }
    public void onQuit(View view) {
        Intent intent = new Intent(this, SimpleTitleScreen.class);
        startActivity(intent);
    }

}