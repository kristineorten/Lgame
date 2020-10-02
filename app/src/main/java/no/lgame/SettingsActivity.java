package no.lgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    public static final String INFO_COLOR_MODE = "no.lgame.MESSAGE_INFO_COLOR_MODE";
    public static final String INFO_COLOR_L1 = "no.lgame.MESSAGE_INFO_COLOR_L1";
    public static final String INFO_COLOR_L2 = "no.lgame.MESSAGE_INFO_COLOR_L2";
    public static final String INFO_WINNUMBER_P1 = "no.lgame.MESSAGE_INFO_WINNUMBER_P1";
    public static final String INFO_WINNUMBER_P2 = "no.lgame.MESSAGE_INFO_WINNUMBER_P2";
    public static final String INFO_WINNUMBER_YOU = "no.lgame.MESSAGE_INFO_WINNUMBER_YOU";
    public static final String INFO_WINNUMBER_AI = "no.lgame.MESSAGE_INFO_WINNUMBER_AI";
    ArrayList<View> viewList;
    ArrayList<TextView> textList;
    RadioButton darkMode;
    RadioButton lightMode;
    View colorSelectedL1;
    View colorSelectedL2;
    String colorMode;
    String colorL1;
    String colorL2;
    ImageButton arrowImg;
    String winNr1;
    String winNr2;
    String winNrYou;
    String winNrAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(MainActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(MainActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(MainActivity.INFO_COLOR_L2);

        if (colorMode == null) {
            colorMode = "light";
        } if (colorL1 == null) {
            colorL1 = "Red";
        }
        if (colorL2 == null) {
            colorL2 = "Blue";
        }

        winNr1 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P2);

        winNrYou = intent.getStringExtra(MainActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(MainActivity.INFO_WINNUMBER_AI);

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        colorActivity();

        colorSelectedL1.setBackgroundResource(R.drawable.color_btn_selected);
        colorSelectedL2.setBackgroundResource(R.drawable.color_btn_selected);

        RadioButton radioBtn;
        if (colorMode.equals("light")) {
            radioBtn = (RadioButton) findViewById(R.id.lightMode);
        } else {
            radioBtn = (RadioButton) findViewById(R.id.darkMode);
        }
        radioBtn.setChecked(true);
    }

    private void colorActivity() {
        viewList = new ArrayList<>();

        viewList.add(findViewById(R.id.conLayout));
        viewList.add(findViewById(R.id.backArrow));

        textList = new ArrayList<>();

        textList.add((TextView) findViewById(R.id.textView));
        textList.add((TextView) findViewById(R.id.textView8));
        textList.add((TextView) findViewById(R.id.textView9));
        textList.add((TextView) findViewById(R.id.settingsTitle));

        darkMode = (RadioButton) findViewById(R.id.darkMode);
        lightMode = (RadioButton) findViewById(R.id.lightMode);
        arrowImg = (ImageButton) findViewById(R.id.backArrow);

        setColorValueL1();
        setColorValueL2();

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
        darkMode.setTextColor(color2);
        lightMode.setTextColor(color2);
        arrowImg.setColorFilter(color2);
    }

    private void setColorValueL1() {
        if (colorL1.equals("Purple")) {
            colorSelectedL1 = findViewById(R.id.btnPurple1);
        } else if (colorL1.equals("Red")) {
            colorSelectedL1 = findViewById(R.id.btnRed1);
        } else if (colorL1.equals("Orange")) {
            colorSelectedL1 = findViewById(R.id.btnOrange1);
        } else if (colorL1.equals("Yellow")) {
            colorSelectedL1 = findViewById(R.id.btnYellow1);
        } else if (colorL1.equals("Blue")) {
            colorSelectedL1 = findViewById(R.id.btnBlue1);
        } else if (colorL1.equals("Aqua")) {
            colorSelectedL1 = findViewById(R.id.btnAqua1);
        } else if (colorL1.equals("LightGreen")) {
            colorSelectedL1 = findViewById(R.id.btnLightGreen1);
        } else if (colorL1.equals("Green")) {
            colorSelectedL1 = findViewById(R.id.btnGreen1);
        }
    }

    private void setColorValueL2() {
        if (colorL2.equals("Purple")) {
            colorSelectedL2 = findViewById(R.id.btnPurple2);
        } else if (colorL2.equals("Red")) {
            colorSelectedL2 = findViewById(R.id.btnRed2);
        } else if (colorL2.equals("Orange")) {
            colorSelectedL2 = findViewById(R.id.btnOrange2);
        } else if (colorL2.equals("Yellow")) {
            colorSelectedL2 = findViewById(R.id.btnYellow2);
        } else if (colorL2.equals("Blue")) {
            colorSelectedL2 = findViewById(R.id.btnBlue2);
        } else if (colorL2.equals("Aqua")) {
            colorSelectedL2 = findViewById(R.id.btnAqua2);
        } else if (colorL2.equals("LightGreen")) {
            colorSelectedL2 = findViewById(R.id.btnLightGreen2);
        } else if (colorL2.equals("Green")) {
            colorSelectedL2 = findViewById(R.id.btnGreen2);
        }
    }

    public void changeColorMode(View view) {
        // Check which radio button was clicked
        if (view.getId() == R.id.lightMode) {
            colorMode = "light";
            colorView(Color.WHITE, Color.DKGRAY);
        } else if (view.getId() == R.id.darkMode) {
            colorMode = "dark";
            colorView(Color.BLACK, Color.WHITE);
        }
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

    public void changeColorL1(View view) {
        if (view.getId() != colorSelectedL1.getId() && !fromViewIdToColor(view).equals(fromViewIdToColor(colorSelectedL2))) {
            View btnClicked = findViewById(view.getId());
            btnClicked.setBackgroundResource(R.drawable.color_btn_selected);
            colorSelectedL1.setBackgroundResource(R.drawable.color_btn);
            colorSelectedL1 = findViewById(view.getId());
            colorL1 = fromViewIdToColor(colorSelectedL1);
        }
    }

    public void changeColorL2(View view) {
        if (view.getId() != colorSelectedL2.getId() && !fromViewIdToColor(view).equals(fromViewIdToColor(colorSelectedL1))) {
            View btnClicked = findViewById(view.getId());
            btnClicked.setBackgroundResource(R.drawable.color_btn_selected);
            colorSelectedL2.setBackgroundResource(R.drawable.color_btn);
            colorSelectedL2 = findViewById(view.getId());
            colorL2 = fromViewIdToColor(colorSelectedL2);
        }
    }


    private String fromViewIdToColor(View btn) {
        String id = String.valueOf(getResources().getResourceEntryName(btn.getId()));
        char[] id_chars = id.toCharArray();
        String color = "";
        for (int i = 3; i < id_chars.length-1; i++) {
            color += id_chars[i];
        }
        return color;
    }
}