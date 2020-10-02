package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GameOverActivity extends AppCompatActivity {
    public static final String INFO_COLOR_MODE = "no.lgame.MESSAGE_INFO_COLOR_MODE";
    public static final String INFO_COLOR_L1 = "no.lgame.MESSAGE_INFO_COLOR_L1";
    public static final String INFO_COLOR_L2 = "no.lgame.MESSAGE_INFO_COLOR_L2";
    public static final String INFO_WINNER = "no.lgame.MESSAGE_INFO_WINNER";
    public static final String INFO_WINNUMBER_P1 = "no.lgame.MESSAGE_INFO_WINNUMBER_P1";
    public static final String INFO_WINNUMBER_P2 = "no.lgame.MESSAGE_INFO_WINNUMBER_P2";
    public static final String INFO_WINNUMBER_YOU = "no.lgame.MESSAGE_INFO_WINNUMBER_YOU";
    public static final String INFO_WINNUMBER_AI = "no.lgame.MESSAGE_INFO_WINNUMBER_AI";
    String winner2P;
    ArrayList<View> viewList;
    ArrayList<TextView> textList;
    ArrayList<Button> btnList;
    String colorMode;
    String colorL1;
    String colorL2;

    String color;
    String winNr1;
    String winNr2;
    String winNrAI;
    String winNrYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(TwoPlayerActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(TwoPlayerActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(TwoPlayerActivity.INFO_COLOR_L2);
        winner2P = intent.getStringExtra(TwoPlayerActivity.INFO_WINNER);
        winNr1 = intent.getStringExtra(TwoPlayerActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(TwoPlayerActivity.INFO_WINNUMBER_P2);
        winNrYou = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_AI);

        if (winNr1 == null && winNr2 == null) {
            winNr1 = "0";
            winNr2 = "0";
        }

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        colorActivity();

        TextView winnerText = (TextView) findViewById(R.id.textWinner);
        winnerText.setText(winner2P);

        TextView winnerColor = (TextView) findViewById(R.id.colorWinner);
        if (winner2P.equals("Player 1")) {
            color = "(" + colorL1 + ")";
            int winNrInt = Integer.parseInt(winNr1) + 1;
            winNr1 = "" + winNrInt;
        } else {
            color = "(" + colorL2 + ")";
            int winNrInt = Integer.parseInt(winNr2) + 1;
            winNr2 = "" + winNrInt;
        }
        winnerColor.setText(color);
    }

    private void colorActivity() {
        if (colorMode == null) {
            colorMode = "light";
        }
        if (colorL1 == null) {
            colorL1 = "Red";
        }
        if (colorL2 == null) {
            colorL2 = "Blue";
        }

        viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.conLayout));
        viewList.add(findViewById(R.id.home));
        viewList.add(findViewById(R.id.twoPlayer));
        viewList.add(findViewById(R.id.onePlayer));

        textList = new ArrayList<>();

        textList.add((TextView) findViewById(R.id.textView20));
        textList.add((TextView) findViewById(R.id.textWinner));
        textList.add((TextView) findViewById(R.id.colorWinner));

        btnList = new ArrayList<>();

        btnList.add((Button) findViewById(R.id.home));
        btnList.add((Button) findViewById(R.id.twoPlayer));
        btnList.add((Button) findViewById(R.id.onePlayer));

        if (colorMode.equals("light")) {
            colorView(Color.WHITE, Color.DKGRAY);
        } else if (colorMode.equals("dark")) {
            colorView(Color.BLACK, Color.WHITE);
        }
    }

    private void colorView(int color1, int color2) {
        for (View v : viewList) {
            v.setBackgroundColor(color1);
        }
        for (TextView t : textList) {
            t.setTextColor(color2);
        }
        for (Button b : btnList) {
            b.setTextColor(color2);
        }
    }

    public void goToMain(View view) {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.putExtra(INFO_COLOR_MODE, colorMode);
        intentMain.putExtra(INFO_COLOR_L1, colorL1);
        intentMain.putExtra(INFO_COLOR_L2, colorL2);
        intentMain.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentMain.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentMain.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentMain.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentMain);
    }

    public void goToTwoPlayer(View view) {
        Intent intentTwoPlayer = new Intent(this, TwoPlayerActivity.class);
        intentTwoPlayer.putExtra(INFO_COLOR_MODE, colorMode);
        intentTwoPlayer.putExtra(INFO_COLOR_L1, colorL1);
        intentTwoPlayer.putExtra(INFO_COLOR_L2, colorL2);
        intentTwoPlayer.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentTwoPlayer.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentTwoPlayer.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentTwoPlayer.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentTwoPlayer);
    }

    public void goToOnePlayer(View view) {
        Intent intentOnePlayer = new Intent(this, LevelActivity.class);
        intentOnePlayer.putExtra(INFO_COLOR_MODE, colorMode);
        intentOnePlayer.putExtra(INFO_COLOR_L1, colorL1);
        intentOnePlayer.putExtra(INFO_COLOR_L2, colorL2);
        intentOnePlayer.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentOnePlayer.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentOnePlayer.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentOnePlayer.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentOnePlayer);
    }
}