package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {
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
    ArrayList<TextView> textViewList;
    ArrayList<View> viewList;
    ImageButton arrowImg;
    String winNr1;
    String winNr2;
    String winNrYou;
    String winNrAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

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

    public void goToMain(View view) {
        Intent goToMain = new Intent(this, MainActivity.class);
        goToMain.putExtra(INFO_COLOR_MODE, colorMode);
        goToMain.putExtra(INFO_COLOR_L1, colorL1);
        goToMain.putExtra(INFO_COLOR_L2, colorL2);
        goToMain.putExtra(INFO_WINNUMBER_P1, winNr1);
        goToMain.putExtra(INFO_WINNUMBER_P2, winNr2);
        goToMain.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        goToMain.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(goToMain);
    }

    private void colorActivity() {
        textViewList = new ArrayList<>();

        textViewList.add((TextView) findViewById(R.id.textView6));
        textViewList.add((TextView) findViewById(R.id.textView14));
        textViewList.add((TextView) findViewById(R.id.textView15));

        viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.conLayout));
        viewList.add(findViewById(R.id.backArrow2));

        arrowImg = (ImageButton) findViewById(R.id.backArrow2);

        if (colorMode.equals("light")) {
            colorView(Color.DKGRAY, Color.WHITE);
        } else if (colorMode.equals("dark")) {
            colorView(Color.WHITE, Color.BLACK);
        }
    }

    private void colorView(int color1, int color2) {
        for (TextView v : textViewList) {
            v.setTextColor(color1);
        }
        for (View v : viewList) {
            v.setBackgroundColor(color2);
        }
        arrowImg.setColorFilter(color1);
    }

    public void goToAbout(View view) {
        Intent intentAbout = new Intent(this, AboutActivity.class);
        intentAbout.putExtra(INFO_COLOR_MODE, colorMode);
        intentAbout.putExtra(INFO_COLOR_L1, colorL1);
        intentAbout.putExtra(INFO_COLOR_L2, colorL2);
        intentAbout.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentAbout.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentAbout.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentAbout.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentAbout);
    }

    public void goToRules1(View view) {
        Intent intentRules = new Intent(this, RulesActivity.class);
        intentRules.putExtra(INFO_COLOR_MODE, colorMode);
        intentRules.putExtra(INFO_COLOR_L1, colorL1);
        intentRules.putExtra(INFO_COLOR_L2, colorL2);
        intentRules.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentRules.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentRules.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentRules.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentRules);
    }

    public void goToRules2(View view) {
        Intent intentRules2 = new Intent(this, RulesActivity2.class);
        intentRules2.putExtra(INFO_COLOR_MODE, colorMode);
        intentRules2.putExtra(INFO_COLOR_L1, colorL1);
        intentRules2.putExtra(INFO_COLOR_L2, colorL2);
        intentRules2.putExtra(INFO_WINNUMBER_P1, winNr1);
        intentRules2.putExtra(INFO_WINNUMBER_P2, winNr2);
        intentRules2.putExtra(INFO_WINNUMBER_YOU, winNrYou);
        intentRules2.putExtra(INFO_WINNUMBER_AI, winNrAI);
        startActivity(intentRules2);
    }
}