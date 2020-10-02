package no.lgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    public static final String INFO_COLOR_MODE = "no.lgame.MESSAGE_INFO_COLOR_MODE";
    public static final String INFO_COLOR_L1 = "no.lgame.MESSAGE_INFO_COLOR_L1";
    public static final String INFO_COLOR_L2 = "no.lgame.MESSAGE_INFO_COLOR_L2";
    public static final String INFO_WINNUMBER_P1 = "no.lgame.MESSAGE_INFO_WINNUMBER_P1";
    public static final String INFO_WINNUMBER_P2 = "no.lgame.MESSAGE_INFO_WINNUMBER_P2";
    public static final String INFO_WINNUMBER_YOU = "no.lgame.MESSAGE_INFO_WINNUMBER_YOU";
    public static final String INFO_WINNUMBER_AI = "no.lgame.MESSAGE_INFO_WINNUMBER_AI";
    String colorMode;
    String colorL1;
    String colorL2;
    ArrayList<View> viewList;
    ArrayList<Button> btnList;
    TextView gameTitle;
    String winNr1;
    String winNr2;
    String winNrYou;
    String winNrAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(SettingsActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(SettingsActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(SettingsActivity.INFO_COLOR_L2);

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        colorActivity();

        winNr1 = intent.getStringExtra(GameOverActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(GameOverActivity.INFO_WINNUMBER_P2);
        if (winNr1 == null && winNr2 == null) {
            winNr1 = intent.getStringExtra(TwoPlayerActivity.INFO_WINNUMBER_P1);
            winNr2 = intent.getStringExtra(TwoPlayerActivity.INFO_WINNUMBER_P2);
        }

        winNrYou = intent.getStringExtra(GameOverOnePlayerActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(GameOverOnePlayerActivity.INFO_WINNUMBER_AI);
        if (winNrYou == null && winNrAI == null) {
            winNrYou = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_YOU);
            winNrAI = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_AI);
        }
        if (winNrYou == null && winNrAI == null) {
            winNrYou = intent.getStringExtra(LevelActivity.INFO_WINNUMBER_YOU);
            winNrAI = intent.getStringExtra(LevelActivity.INFO_WINNUMBER_AI);
        }

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);
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
        viewList.add(findViewById(R.id.settingsBtn));
        viewList.add(findViewById(R.id.helpBtn));
        viewList.add(findViewById(R.id.onePlayer));
        viewList.add(findViewById(R.id.twoPlayers));
        viewList.add(findViewById(R.id.stats));

        btnList = new ArrayList<>();

        btnList.add((Button) findViewById(R.id.onePlayer));
        btnList.add((Button) findViewById(R.id.twoPlayers));
        btnList.add((Button) findViewById(R.id.stats));

        gameTitle = (TextView) findViewById(R.id.gameTitle);

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
        for (Button b : btnList) {
            b.setTextColor(color2);
        }
        gameTitle.setTextColor(color2);
    }

    public void playOnePlayer(View view) {
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

    public void playTwoPlayers(View view) {
        Intent intentTwoPlayers = new Intent(this, TwoPlayerActivity.class);
        intentTwoPlayers.putExtra(INFO_COLOR_MODE, colorMode);
        intentTwoPlayers.putExtra(INFO_COLOR_L1, colorL1);
        intentTwoPlayers.putExtra(INFO_COLOR_L2, colorL2);
        intentTwoPlayers.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentTwoPlayers.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentTwoPlayers.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentTwoPlayers.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentTwoPlayers);
    }

    public void goToStats(View view) {
        Intent intentStats = new Intent(this, StatsActivity.class);
        intentStats.putExtra(INFO_COLOR_MODE, colorMode);
        intentStats.putExtra(INFO_COLOR_L1, colorL1);
        intentStats.putExtra(INFO_COLOR_L2, colorL2);
        intentStats.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentStats.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentStats.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentStats.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentStats);
    }

    public void goToSettings(View view) {
        Intent intentSettings = new Intent(this, SettingsActivity.class);
        intentSettings.putExtra(INFO_COLOR_MODE, colorMode);
        intentSettings.putExtra(INFO_COLOR_L1, colorL1);
        intentSettings.putExtra(INFO_COLOR_L2, colorL2);
        intentSettings.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentSettings.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentSettings.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentSettings.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentSettings);
    }

    public void goToHelp(View view) {
        Intent intentHelp = new Intent(this, HelpActivity.class);
        intentHelp.putExtra(INFO_COLOR_MODE, colorMode);
        intentHelp.putExtra(INFO_COLOR_L1, colorL1);
        intentHelp.putExtra(INFO_COLOR_L2, colorL2);
        intentHelp.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentHelp.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentHelp.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentHelp.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentHelp);
    }
}