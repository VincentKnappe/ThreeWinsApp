package com.example.threewinsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int activePlayer = 0;
    //player 1 = 0 and player 2 = 1. The array represents the game board and shows,
    //which position was blocked by which player.
    int[] gameStatus = {9, 9, 9, 9, 9, 9, 9, 9, 9};
    int [][] winningConditions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    Button btn_restart;
    TextView tv_winner;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        Log.i("Tag",counter.getTag().toString());


        if(gameStatus[tag] == 9 && gameActive){
            //set image above the image view.
            counter.setTranslationY(-1500);
            gameStatus[tag] = activePlayer;

            if (activePlayer == 0){
                //resource for the image view.
                counter.setImageResource(R.drawable.player1);
                activePlayer ++;
            }else{
                //resource for the image view.
                counter.setImageResource(R.drawable.player2);
                activePlayer --;
            }
            //animation witch includes the position shift from above the image view to the center of it.
            //Accompanied by a rotation.
            counter.animate().translationYBy(1500).rotation(720).setDuration(1500);
            for (int[] winningCondition: winningConditions){
                if (gameStatus[winningCondition[0]] == gameStatus[winningCondition[1]] && gameStatus[winningCondition[1]] == gameStatus[winningCondition[2]] && gameStatus[winningCondition[0]] !=9){
                    String winner ="";
                    gameActive = false;
                    if (activePlayer ==1){
                        winner = "Spieler 1";
                    }else{
                        winner = "Spieler 2";
                    }
                    //Toast.makeText(this, winner +" hat gewonnen", Toast.LENGTH_SHORT).show();


                    tv_winner.setText(winner + " hat das Spiel gewonnen!");
                    btn_restart.setVisibility(View.VISIBLE);
                    tv_winner.setVisibility(View.VISIBLE);
                }else{
                    gameActive= false;
                    for (int counterStatus : gameStatus ){
                        if (counterStatus == 9) gameActive = true;
                    }
                    if (!gameActive){
                        tv_winner.setText(" Unentschieden!");
                        btn_restart.setVisibility(View.VISIBLE);
                        tv_winner.setVisibility(View.VISIBLE);
                    }
                };
            }
        }
    }
    public  void restart(View view){
        btn_restart.setVisibility(View.INVISIBLE);
        tv_winner.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);

        for (int i = 0; i< grid.getChildCount(); i++){
            ImageView gridCounter = (ImageView) grid.getChildAt(i);
            gridCounter.setImageDrawable(null);
        }
        gameActive = true;
        activePlayer = 0;
        for (int i=0; i< gameStatus.length; i++){
            gameStatus[i] = 9;
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btn_restart = findViewById(R.id.btn_restart);
         tv_winner= findViewById(R.id.tv_winner);

    }
}