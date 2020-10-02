package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(MainActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(MainActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(MainActivity.INFO_COLOR_L2);

        winNr1 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P2);

        if (winNr1 == null && winNr2 == null) {
            winNr1 = "0";
            winNr2 = "0";
        }

        winNrYou = intent.getStringExtra(MainActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(MainActivity.INFO_WINNUMBER_AI);

        if (winNrYou == null && winNrAI == null) {
            winNrYou = "0 - 0 - 0";
            winNrAI = "0 - 0 - 0";
        }

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        setText();

        TextView winText1 = (TextView) findViewById(R.id.winsP1);
        TextView winText2 = (TextView) findViewById(R.id.winsP2);

        SpannableString textP1 = new SpannableString("Wins " + colorL1 + ": ");
        textP1.setSpan(new StyleSpan(Typeface.BOLD), 0, textP1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString textP2 = new SpannableString("Wins " + colorL2 + ": ");
        textP2.setSpan(new StyleSpan(Typeface.BOLD), 0, textP2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        winText1.setText(textP1);
        winText2.setText(textP2);

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2);

        colorActivity();
    }

    private void setText() {
        // One player
        TextView winYou = (TextView) findViewById(R.id.winNrYou);
        TextView winAI = (TextView) findViewById(R.id.winNrAI);
        winYou.setText(winNrYou);
        winAI.setText(winNrAI);

        // Two players
        TextView win1 = (TextView) findViewById(R.id.winNr1);
        TextView win2 = (TextView) findViewById(R.id.winNr2);
        win1.setText(winNr1);
        win2.setText(winNr2);
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

        textViewList = new ArrayList<>();

        textViewList.add((TextView) findViewById(R.id.textView17));
        textViewList.add((TextView) findViewById(R.id.winsP1));
        textViewList.add((TextView) findViewById(R.id.winsP2));
        textViewList.add((TextView) findViewById(R.id.winsYou));
        textViewList.add((TextView) findViewById(R.id.winsAI));
        textViewList.add((TextView) findViewById(R.id.winNr1));
        textViewList.add((TextView) findViewById(R.id.winNr2));
        textViewList.add((TextView) findViewById(R.id.winNrYou));
        textViewList.add((TextView) findViewById(R.id.winNrAI));
        textViewList.add((TextView) findViewById(R.id.twoPlayers));
        textViewList.add((TextView) findViewById(R.id.onePlayer));
        textViewList.add((TextView) findViewById(R.id.levels));

        viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.conLayout));
        viewList.add(findViewById(R.id.backArrow6));

        arrowImg = (ImageButton) findViewById(R.id.backArrow6);

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

    public void resetTwoPlayer(View view) {
        winNr1 = "0";
        winNr2 = "0";
        setText();
    }

    public void resetOnePlayer(View view) {
        winNrYou = "0 - 0 - 0";
        winNrAI = "0 - 0 - 0";
        setText();
    }
}