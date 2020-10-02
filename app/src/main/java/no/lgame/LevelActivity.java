package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {
    public static final String INFO_COLOR_MODE = "no.lgame.MESSAGE_INFO_COLOR_MODE";
    public static final String INFO_COLOR_L1 = "no.lgame.MESSAGE_INFO_COLOR_L1";
    public static final String INFO_COLOR_L2 = "no.lgame.MESSAGE_INFO_COLOR_L2";
    public static final String INFO_LEVEL = "no.lgame.MESSAGE_INFO_LEVEL";
    public static final String INFO_WINNUMBER_P1 = "no.lgame.MESSAGE_INFO_WINNUMBER_P1";
    public static final String INFO_WINNUMBER_P2 = "no.lgame.MESSAGE_INFO_WINNUMBER_P2";
    public static final String INFO_WINNUMBER_YOU = "no.lgame.MESSAGE_INFO_WINNUMBER_YOU";
    public static final String INFO_WINNUMBER_AI = "no.lgame.MESSAGE_INFO_WINNUMBER_AI";
    String colorMode;
    String colorL1;
    String colorL2;
    String level;
    String winNr1;
    String winNr2;
    String winNrYou;
    String winNrAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(MainActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(MainActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(MainActivity.INFO_COLOR_L2);
        winNr1 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P2);
        winNrYou = intent.getStringExtra(MainActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(MainActivity.INFO_WINNUMBER_AI);

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        colorActivity();
    }

    public void btnClicked(View view) {
        if (view.getId() == R.id.btnEasy) {
            level = "1";
        } else if (view.getId() == R.id.btnNormal) {
            level = "2";
        } else {
            level = "3";
        }
        goToOnePlayer();
    }

    public void goToOnePlayer() {
        Intent intentOnePlayer = new Intent(this, OnePlayerActivity.class);
        intentOnePlayer.putExtra(INFO_COLOR_MODE, colorMode);
        intentOnePlayer.putExtra(INFO_COLOR_L1, colorL1);
        intentOnePlayer.putExtra(INFO_COLOR_L2, colorL2);
        intentOnePlayer.putExtra(INFO_LEVEL, level);
        intentOnePlayer.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentOnePlayer.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentOnePlayer.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentOnePlayer.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentOnePlayer);
    }

    /*public void goToMain(View view) {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.putExtra(INFO_COLOR_MODE, colorMode);
        intentMain.putExtra(INFO_COLOR_L1, colorL1);
        intentMain.putExtra(INFO_COLOR_L2, colorL2);
        startActivity(intentMain);
    }*/

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

        ArrayList<Button> btnList = new ArrayList<>();

        btnList.add((Button) findViewById(R.id.btnEasy));
        btnList.add((Button) findViewById(R.id.btnNormal));
        btnList.add((Button) findViewById(R.id.btnHard));

        ArrayList<View> viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.btnEasy));
        viewList.add(findViewById(R.id.btnNormal));
        viewList.add(findViewById(R.id.btnHard));
        viewList.add(findViewById(R.id.conLayout));

        if (colorMode.equals("light")) {
            for (Button b : btnList) {
                b.setTextColor(Color.DKGRAY);
            }for (View v : viewList) {
                v.setBackgroundColor(Color.WHITE);
            }
        } else if (colorMode.equals("dark")) {
            for (Button b : btnList) {
                b.setTextColor(Color.WHITE);
            } for (View v : viewList) {
                v.setBackgroundColor(Color.BLACK);
            }
        }
    }
}