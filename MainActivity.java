package fr.ghippodam.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button pion0, pion1, pion2, pion3, pion4, pion5, pion6, pion7, pion8;
    Button[] pions = new Button[9];
    ImageView logo;
    TextView gameStatus;
    Button replayButton;
    boolean gameIsOver = false;
    int currentTurn = 0;
    String joueurs[] = {"X", "0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        bindEvenListenertOnButtons();
    }

    private void bindEvenListenertOnButtons() {
        for(Button pion : pions) {
            pion.setOnClickListener(pionListerner);
        }
        gameIsOver = false;
        gameStatus.setText(R.string.game_status_text);
        replayButton.setOnClickListener(replayListener);
    }
    private View.OnClickListener pionListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) findViewById(v.getId());
            if (gameIsOver){
                return ;
            }
            if (!isValid(button)){
                logo.animate();
               // animationShake();
                gameStatus.setText("Deplacement invalide");
            } else{
                setSymbol(button,joueurs[currentTurn]);
                gameIsOver = winnerExists();
                if (gameIsOver){
                    gameStatus.setText("Le joueur " + joueurs[currentTurn] + " a gagne!");
                }
                if(boardIsFull() && !gameIsOver){
                    gameStatus.setText("MATCH NUL!");
                    gameIsOver = true;
                    return;
                }
                currentTurn = currentTurn ^ 1; // Ou exclusif
                if (gameIsOver){
                    gameStatus.setText("Le joueur " + joueurs[currentTurn ^ 1 ] + " a gagne des macarons!");
                }else {
                    gameStatus.setText("Le joueur " + joueurs[currentTurn] + " c \'est a votre tour");
                }

            }
        }
    };

    //private void animationShake() {
        //Animation shake;
        //shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        //logo.startAnimation(shake)
   // }

    private boolean winnerExists() {
        if (pions[0].getText().toString() == joueurs[currentTurn]
                && pions[1].getText().toString() == joueurs[currentTurn]
                && pions[2].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(0, 1, 2);
            return true;
        }
        if (pions[3].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[5].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(3, 4, 5);
            return true;
        }
        if (pions[6].getText().toString() == joueurs[currentTurn]
                && pions[7].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(6, 7, 8);
            return true;
        }
        if (pions[0].getText().toString() == joueurs[currentTurn]
                && pions[3].getText().toString() == joueurs[currentTurn]
                && pions[6].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(0, 3, 6);
            return true;
        }
        if (pions[1].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[7].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(1, 4, 7);
            return true;
        }
        if (pions[2].getText().toString() == joueurs[currentTurn]
                && pions[5].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(2, 5, 8);
            return true;
        }
        if (pions[0].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[8].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(0, 4, 8);
            return true;
        }
        if (pions[2].getText().toString() == joueurs[currentTurn]
                && pions[4].getText().toString() == joueurs[currentTurn]
                && pions[6].getText().toString() == joueurs[currentTurn]){
            changeColorOfButtons(2, 4, 6);
            return true;
        }
        return  false;
    }

    private void changeColorOfButtons(int i, int j, int k) {
        pions[i].setTextColor(Color.argb(255,8,13,246));
        pions[j].setTextColor(Color.argb(255,42,188,18));
        pions[k].setTextColor(Color.argb(255,230,79,97));
    }

    private boolean isValid(Button button) {
        return button.getText().toString().length() == 0;
    };

    private boolean boardIsFull() {
        for(Button pion : pions){
            if (isValid(pion)){
                return  false;
            }
        }
        return true;
    }


    private void setSymbol(Button button, String symbol) {
        button.setText(symbol);
    }

    private View.OnClickListener replayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(Button pion : pions){
                pion.setText("");
                pion.setTextColor(Color.BLACK);
            }
            gameStatus.setText(R.string.game_status_text);
            currentTurn = 0;
            gameIsOver = false;

        }
    };
    private void initialize() {
        pion0 = (Button)findViewById(R.id.pion0Button);
        pion1 = (Button)findViewById(R.id.pion1Button);
        pion2 = (Button)findViewById(R.id.pion2Button);
        pion3 = (Button)findViewById(R.id.pion3Button);
        pion4 = (Button)findViewById(R.id.pion4Button);
        pion5 = (Button)findViewById(R.id.pion5Button);
        pion6 = (Button)findViewById(R.id.pion6Button);
        pion7 = (Button)findViewById(R.id.pion7Button);
        pion8 = (Button)findViewById(R.id.pion8Button);

        logo = (ImageView)findViewById(R.id.image_name);
        gameStatus = (TextView)findViewById(R.id.gamesStatusTextView);
        replayButton = (Button)findViewById(R.id.gameReplayButton);
        pions[0] = pion0;
        pions[1] = pion1;
        pions[2] = pion2;
        pions[3] = pion3;
        pions[4] = pion4;
        pions[5] = pion5;
        pions[6] = pion6;
        pions[7] = pion7;
        pions[8] = pion8;
    }

}
