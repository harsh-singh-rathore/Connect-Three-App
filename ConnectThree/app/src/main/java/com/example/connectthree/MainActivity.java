package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 is yellow and 1 is red and 2 represents empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isGameActive = true;

    public void playAgain(View view) {
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        //GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        isGameActive = true;
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int taggedCounter = Integer.parseInt(counter.getTag().toString());
        if(isGameActive && gameState[taggedCounter] == 2) {
            gameState[taggedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[0]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Someone has won!!!
                    String winner = "";
                    if (activePlayer == 1) {
                        //since the active player has been changed during the above process so here if activePlayer is 1 yellow has won and if the activePlayer is 0 Red has won
                        winner = "Yellow Player";
                    } else if (activePlayer == 0) {
                        //since the active player has been changed during the above process so here if activePlayer is 1 yellow has won and if the activePlayer is 0 Red has won
                        winner = "Red Player";
                    }
                    isGameActive = false;
                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                    winnerText.setText(winner + " has won!!!!");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
