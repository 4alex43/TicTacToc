package com.example.tictactoc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//implements(יישום) View.OnClickListener for all the class
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//Declaration of all variables that will be global for the entire class
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    private char turn = 'x';
    private TextView turnTv;//For play tow players
    private TextView whoWonTV;
    private Button[][] gameBoard;
    private int countClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Implementation of buttons according to the names of the buttons
        whoWonTV = findViewById(R.id.win);
        turnTv = findViewById(R.id.turnTv);
        btn1  = findViewById(R.id.btn1);
        btn2  = findViewById(R.id.btn2);
        btn3  = findViewById(R.id.btn3);
        btn4  = findViewById(R.id.btn4);
        btn5  = findViewById(R.id.btn5);
        btn6  = findViewById(R.id.btn6);
        btn7  = findViewById(R.id.btn7);
        btn8  = findViewById(R.id.btn8);
        btn9  = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
//Set listener for all buttons with this (public void onClick(View view) method)
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

//Set all buttons in array Buttons (gameBoard) with all buttons
        gameBoard = new Button[][]{
                {btn1, btn2, btn3},
                {btn4, btn5, btn6},
                {btn7, btn8, btn9}
        };
        countClicks = 0;
    }
//Global OnClickListener method for all setOnClickListener in buttons
    @Override
    public void onClick(View view) {
//version 1.1
        //if click on newGame button rest all the gameBoard
        if (R.id.btn10 == view.getId()) {
            newGame();
        }
        //For the others buttons fill with who game now
        else {
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (view.getId() == gameBoard[i][j].getId()) {
                        gameBoard[i][j].setText(String.valueOf(turn));
                        gameBoard[i][j].setEnabled(false);
                            if (gameBoard[i][j].getText().equals("x") && countClicks < 8) {
                            nextTurn(turn);
                            playWithPc();
                            gameBoard[i][j].setBackgroundColor((Color.RED));//Changes color according to each player
                            countClicks+=2;
                        }

//If you wont to play 2 players MARK lines with /* between lines 74 to 80 and after */
/*
                        if (gameBoard[i][j].getText().equals("o")) {
                            gameBoard[i][j].setText(String.valueOf(turn));//Fill the button with 'o'
                            gameBoard[i][j].setEnabled(false);//Blocked the button to click
                            gameBoard[i][j].setBackgroundColor((Color.GREEN));//Changes color according to each player

                        }

 */
                        else {
                            gameBoard[i][j].setText(String.valueOf(turn));
                            gameBoard[i][j].setEnabled(false);
                            gameBoard[i][j].setBackgroundColor((Color.RED));//Changes color according to each player
                        }
                        nextTurn(turn);
                        break;
                    }

                }
            }
        }
        checkForWin();//for run the method after clicks
// version 1 with switch cases
 /*
 version 1
        switch (view.getId()) {
            case R.id.btn1:
                btn1.setText(String.valueOf(turn));
                btn1.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn2:
                btn2.setText(String.valueOf(turn));
                btn2.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn3:
                btn3.setText(String.valueOf(turn));
                btn3.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn4:
                btn4.setText(String.valueOf(turn));
                btn4.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn5:
                btn5.setText(String.valueOf(turn));
                btn5.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn6:
                btn6.setText(String.valueOf(turn));
                btn6.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn7:
                btn7.setText(String.valueOf(turn));
                btn7.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn8:
                btn8.setText(String.valueOf(turn));
                btn8.setEnabled(false);
                nextTurn(turn);
                break;
            case R.id.btn9:
                btn9.setText(String.valueOf(turn));
                btn9.setEnabled(false);
                nextTurn(turn);
                break;
          }

  */
    }
//Method for fill variable turn
    private void setTurn(char turn) {
        this.turn = turn;
    }
//Method for change player to other player
    private char nextTurn(char turn){
        if (turn == 'x'){
            setTurn('o');//change to next player
            turnTv.setText("Turn: o" );//write who play now
        }
        else {
            setTurn('x');//change to next player
            turnTv.setText("Turn: x");//write who play now
        }
        return turn;
    }
//Method for rest the gameBoard for newGame
    public void newGame() {
        turnTv.setText("Turn: x");//change the turn to x (x only first play in new game)
        whoWonTV.setText("Win: ");//Rest who win for newGame
        setTurn('x');
        countClicks = 0;// rest the counts clicks for newGame
        //Rest all the cell in gameBoard for new game
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j].setEnabled(true);//Enabled the button for click
                gameBoard[i][j].setText("");//turn the text to empty for newGame
                gameBoard[i][j].setBackgroundColor((Color.BLUE));//Changes color to blue for all buttons

            }
        }
    }
//Method for disEnabled all buttons after win
    public void disEnabledAllButtons(){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.println( gameBoard[i][j]);
                gameBoard[i][j].setEnabled(false);
            }
        }
    }
//Method for check win
    private void checkForWin() {

        String[][] fieldBoard = new String[3][3];//Board for check win
        //Fill the board with x or o where users fill in gameBoard
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldBoard[i][j] = gameBoard[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            //Check win in Rows
            if (fieldBoard[i][0].equals(fieldBoard[i][1])
                    && fieldBoard[i][0].equals(fieldBoard[i][2])
                    && !fieldBoard[i][0].equals("")) {

                whoWonTV.setText("Win: " + gameBoard[i][0].getText());
                disEnabledAllButtons();
            }
            //Check win in Columns
            if (fieldBoard[0][i].equals(fieldBoard[1][i])
                    && fieldBoard[0][i].equals(fieldBoard[2][i])
                    && !fieldBoard[0][i].equals("")) {

                whoWonTV.setText("Win: " + gameBoard[0][i].getText());
                disEnabledAllButtons();

            }

        }
        //Check win in diagonal line left to right (Down)
        if (fieldBoard[0][0].equals(fieldBoard[1][1])
                && fieldBoard[0][0].equals(fieldBoard[2][2])
                && !fieldBoard[0][0].equals("")) {

            whoWonTV.setText("Win: " + gameBoard[0][0].getText());
            disEnabledAllButtons();
        }
        //Check win in diagonal line left to right (Up)
        if (fieldBoard[0][2].equals(fieldBoard[1][1])
                && fieldBoard[0][2].equals(fieldBoard[2][0])
                && !fieldBoard[0][2].equals("")) {

            whoWonTV.setText("Win: " + gameBoard[0][2].getText());
            disEnabledAllButtons();
        }
    }
//Picks a random number for cells in the array and fill random cell in the gameBoard
    private void playWithPc(){
        int rand1 = (int)(Math.random() * 3) ;//Picks a random number for cells in the array
        int rand2 = (int)(Math.random() * 3) ;//Picks a random number for cells in the array
        //Check if the cell not selected
        if (gameBoard[rand1][rand2].isEnabled() == true){
            gameBoard[rand1][rand2].setText(String.valueOf(turn));//Fill the button with 'o'
            gameBoard[rand1][rand2].setEnabled(false);//Blocked the button to click
            gameBoard[rand1][rand2].setBackgroundColor((Color.GREEN));//Changes color according to each player
        }
        else playWithPc();//If selected run the method again
    }




}