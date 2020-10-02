package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_about);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(HelpActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(HelpActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(HelpActivity.INFO_COLOR_L2);

        winNr1 = intent.getStringExtra(HelpActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(HelpActivity.INFO_WINNUMBER_P2);

        winNrYou = intent.getStringExtra(HelpActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(HelpActivity.INFO_WINNUMBER_AI);

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        colorActivity();
    }

    public void goToWiki(View view) {
        Intent intentWiki = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/L_game"));
        startActivity(intentWiki);
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

    private void colorActivity() {
        textViewList = new ArrayList<>();

        textViewList.add((TextView) findViewById(R.id.textView11));
        textViewList.add((TextView) findViewById(R.id.textView13));
        textViewList.add((TextView) findViewById(R.id.textView16));

        viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.conLayout));
        viewList.add(findViewById(R.id.backArrow4));
        viewList.add(findViewById(R.id.button3));

        arrowImg = (ImageButton) findViewById(R.id.backArrow4);
        Button btn = (Button) findViewById(R.id.button3);
        btn.setTextColor(getColor(android.R.color.holo_blue_light));

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
}