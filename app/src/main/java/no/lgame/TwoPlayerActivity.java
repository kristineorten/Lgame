package no.lgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TwoPlayerActivity extends AppCompatActivity {
    public static final String INFO_COLOR_MODE = "no.lgame.MESSAGE_INFO_COLOR_MODE";
    public static final String INFO_COLOR_L1 = "no.lgame.MESSAGE_INFO_COLOR_L1";
    public static final String INFO_COLOR_L2 = "no.lgame.MESSAGE_INFO_COLOR_L2";
    public static final String INFO_WINNER = "no.lgame.MESSAGE_INFO_WINNER";
    public static final String INFO_WINNUMBER_P1 = "no.lgame.MESSAGE_INFO_WINNUMBER_P1";
    public static final String INFO_WINNUMBER_P2 = "no.lgame.MESSAGE_INFO_WINNUMBER_P2";
    public static final String INFO_WINNUMBER_YOU = "no.lgame.MESSAGE_INFO_WINNUMBER_YOU";
    public static final String INFO_WINNUMBER_AI = "no.lgame.MESSAGE_INFO_WINNUMBER_AI";

    ArrayList<ArrayList<Button>> grid;
    ArrayList<String> occupiedPositions;
    ArrayList<Button> buttonsClicked;
    ArrayList<Button> lShape_1;
    ArrayList<Button> lShape_2;

    Button nSquare_1;
    Button nSquare_2;
    Button nRemoved;
    Button nChanged;
    Button n1_original;
    Button n2_original;

    int playersTurn;
    boolean moveNPiece;
    boolean gameOver;
    String winner;
    String winNr1;
    String winNr2;
    String winNrYou;
    String winNrAI;

    ArrayList<View> viewList;
    ArrayList<TextView> textList;
    String colorMode;
    String colorL1;
    String colorL2;
    int color_L1_int;
    int color_L2_int;
    int color_L1_light;
    int color_L2_light;
    ImageButton arrowImg;

    TextView helpText;
    TextView whoMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        Intent intent = getIntent();
        colorMode = intent.getStringExtra(MainActivity.INFO_COLOR_MODE);
        colorL1 = intent.getStringExtra(MainActivity.INFO_COLOR_L1);
        colorL2 = intent.getStringExtra(MainActivity.INFO_COLOR_L2);

        winNrYou = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_YOU);
        winNrAI = intent.getStringExtra(OnePlayerActivity.INFO_WINNUMBER_AI);

        winNr1 = intent.getStringExtra(GameOverActivity.INFO_WINNUMBER_P1);
        winNr2 = intent.getStringExtra(GameOverActivity.INFO_WINNUMBER_P2);
        if (winNr1 == null && winNr2 == null) {
            winNr1 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P1);
            winNr2 = intent.getStringExtra(MainActivity.INFO_WINNUMBER_P2);
        }
        if (winNr1 == null && winNr2 == null) {
            winNr1 = "0";
            winNr2 = "0";
        }

        System.out.println(colorMode + " " + colorL1 + " " + colorL2 + " " + winNr1 + " " + winNr2 + " " + winNrYou + " " + winNrAI);

        helpText = (TextView) findViewById(R.id.helpText);
        whoMoves = (TextView) findViewById(R.id.playersTurn);
        helpText.setText(R.string.moveL);
        whoMoves.setText(colorL1 + " moves");

        colorActivity();
        setUpGame();
        colorGrid(color_L1_light, color_L2_int);

        TextView infoText = (TextView) findViewById(R.id.info);
        String text = "Wins player 1 (" + colorL1 + "): " + winNr1 + "\nWins player 2 (" + colorL2 + "): " + winNr2;
        infoText.setText(text);
    }

    private void setUpGame() {
        buttonsClicked = new ArrayList<>();
        occupiedPositions = new ArrayList<>();
        lShape_1 = new ArrayList<>();
        lShape_2 = new ArrayList<>();

        makeGrid();
        defineL1();
        defineL2();
        defineN1N2();

        // Defining which player should move and what piece
        playersTurn = 1;
        moveNPiece = false;
        nRemoved = null;
        nChanged = null;
        n1_original = nSquare_1;
        n2_original = nSquare_2;
        winner = "";
    }

    private void makeGrid() {
        grid = new ArrayList<>();

        // Adding columns to the play-grid (x-values)
        for (int i = 0; i < 4; i++) {
            grid.add(new ArrayList<Button>());
        }
        // Adding buttons to the play-grid
        grid.get(0).add((Button) findViewById(R.id.square00)); //(0,0)
        grid.get(1).add((Button) findViewById(R.id.square10)); //(1,0)
        grid.get(2).add((Button) findViewById(R.id.square20));
        grid.get(3).add((Button) findViewById(R.id.square30));
        grid.get(0).add((Button) findViewById(R.id.square01)); //(0,1)
        grid.get(1).add((Button) findViewById(R.id.square11));
        grid.get(2).add((Button) findViewById(R.id.square21));
        grid.get(3).add((Button) findViewById(R.id.square31));
        grid.get(0).add((Button) findViewById(R.id.square02));
        grid.get(1).add((Button) findViewById(R.id.square12));
        grid.get(2).add((Button) findViewById(R.id.square22));
        grid.get(3).add((Button) findViewById(R.id.square32));
        grid.get(0).add((Button) findViewById(R.id.square03));
        grid.get(1).add((Button) findViewById(R.id.square13));
        grid.get(2).add((Button) findViewById(R.id.square23));
        grid.get(3).add((Button) findViewById(R.id.square33));
    }

    private void defineL1() {
        // Making a list over the buttons in L-piece 1
        lShape_1.add(grid.get(1).get(0)); //(1,0)
        lShape_1.add(grid.get(2).get(0)); //(2,0)
        lShape_1.add(grid.get(2).get(1));
        lShape_1.add(grid.get(2).get(2));
    }

    private void defineL2() {
        // Making a list over the buttons in L-piece 2
        lShape_2.add(grid.get(2).get(3));
        lShape_2.add(grid.get(1).get(3));
        lShape_2.add(grid.get(1).get(2));
        lShape_2.add(grid.get(1).get(1));
    }

    private void defineN1N2() {
        // Defining which buttons correspond to the neutral (N) pieces (N1, N2)
        nSquare_1 = grid.get(0).get(0);
        nSquare_2 = grid.get(3).get(3);
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
        viewList.add(findViewById(R.id.backArrow3));

        textList = new ArrayList<>();

        textList.add((TextView) findViewById(R.id.info));
        textList.add(helpText);
        textList.add(whoMoves);

        arrowImg = (ImageButton) findViewById(R.id.backArrow3);

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
        arrowImg.setColorFilter(color2);
    }

    private void setColorValueL1() {
        if (colorL1.equals("Purple")) {
            color_L1_int = R.color.purple;
            color_L1_light = R.color.lightPurple;
        } else if (colorL1.equals("Red")) {
            color_L1_int = R.color.red;
            color_L1_light = R.color.lightRed;
        } else if (colorL1.equals("Orange")) {
            color_L1_int = R.color.orange;
            color_L1_light = R.color.lightOrange;
        } else if (colorL1.equals("Yellow")) {
            color_L1_int = R.color.yellow;
            color_L1_light = R.color.lightYellow;
        } else if (colorL1.equals("Blue")) {
            color_L1_int = R.color.blue;
            color_L1_light = R.color.lightBlue;
        } else if (colorL1.equals("Aqua")) {
            color_L1_int = R.color.aqua;
            color_L1_light = R.color.lightAqua;
        } else if (colorL1.equals("LightGreen")) {
            color_L1_int = R.color.lightGreen;
            color_L1_light = R.color.lighterGreen;
        } else if (colorL1.equals("Green")) {
            color_L1_int = R.color.green;
            color_L1_light = R.color.lightDarkGreen;
        }
    }

    private void setColorValueL2() {
        if (colorL2.equals("Purple")) {
            color_L2_int = R.color.purple;
            color_L2_light = R.color.lightPurple;
        } else if (colorL2.equals("Red")) {
            color_L2_int = R.color.red;
            color_L2_light = R.color.lightRed;
        } else if (colorL2.equals("Orange")) {
            color_L2_int = R.color.orange;
            color_L2_light = R.color.lightOrange;
        } else if (colorL2.equals("Yellow")) {
            color_L2_int = R.color.yellow;
            color_L2_light = R.color.lightYellow;
        } else if (colorL2.equals("Blue")) {
            color_L2_int = R.color.blue;
            color_L2_light = R.color.lightBlue;
        } else if (colorL2.equals("Aqua")) {
            color_L2_int = R.color.aqua;
            color_L2_light = R.color.lightAqua;
        } else if (colorL2.equals("LightGreen")) {
            color_L2_int = R.color.lightGreen;
            color_L2_light = R.color.lighterGreen;
        } else if (colorL2.equals("Green")) {
            color_L2_int = R.color.green;
            color_L2_light = R.color.lightDarkGreen;
        }
    }

    private void colorGrid(int color_L1, int color_L2) {
        colorLpices(color_L1, color_L2);

        colorNpices(android.R.color.black);
        colorTheBoard(android.R.color.white);

        if (colorMode.equals("light")) {
            findViewById(R.id.tableLayout).setBackgroundResource(R.drawable.border_thick);
        } else {
            /*colorNpices(android.R.color.white);
            colorTheBoard(android.R.color.black);*/
            findViewById(R.id.tableLayout).setBackgroundResource(R.drawable.border_thick_darkmode);
        }

        //nSquare_1.setBackgroundResource(R.drawable.color_btn);
        //nSquare_2.setBackgroundResource(R.drawable.color_btn);
    }

    private void colorLpices(int color_L1, int color_L2) {
        for (Button btn_l1 : lShape_1) {
            ViewCompat.setBackgroundTintList(btn_l1, ContextCompat.getColorStateList(this, color_L1));
        }
        // Coloring the buttons (L-piece 2)
        for (Button btn_l2 : lShape_2) {
            ViewCompat.setBackgroundTintList(btn_l2, ContextCompat.getColorStateList(this, color_L2));
        }
    }

    private void colorNpices(int color) {
        ViewCompat.setBackgroundTintList(nSquare_1, ContextCompat.getColorStateList(this, color));
        ViewCompat.setBackgroundTintList(nSquare_2, ContextCompat.getColorStateList(this, color));
    }

    private void colorTheBoard(int color) {
        for (int i = 0; i < grid.size(); i++) {
            for (Button b : grid.get(i)) {
                if (!lShape_1.contains(b) && !lShape_2.contains(b) && (b != nSquare_1) && (b != nSquare_2)) {
                    ViewCompat.setBackgroundTintList(b, ContextCompat.getColorStateList(this, color));
                }
            }
        }
    }

    public void btnClicked(View view) {
        int btnID = view.getId(); // The id of the clicked button
        Button btn = (Button) findViewById(btnID);
        System.out.println("-> " + getResources().getResourceEntryName(btn.getId()));
        if (moveNPiece) {
            if (btn == nSquare_1 || btn == nSquare_2) {
                if (nRemoved == btn) {
                    // Clicked on the previous colored btn
                    ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, android.R.color.black));
                    nRemoved = null;
                    System.out.println("nR: " + nRemoved);
                } else if (nRemoved == null){
                    // There right amount of squares are colored black
                    if (nSquare_1 == n1_original && nSquare_2 == n2_original) {
                        // Board is in original state. Set color to grey
                        ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, R.color.gray));
                        nRemoved = btn;
                    } else {
                        if (btn != n1_original && btn != n2_original) {
                            ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, android.R.color.white));
                            nRemoved = btn;
                            System.out.println("nR: " + getResources().getResourceEntryName(nRemoved.getId()));
                            /*nChanged = null;
                            System.out.println("nC: " + nChanged);*/
                        }
                    }
                }
            } else {
                if (nRemoved != null) {
                    if (!lShape_1.contains(btn) && !lShape_2.contains(btn)) {
                        ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, android.R.color.black));
                        if (nRemoved == nSquare_1) {
                            System.out.println("N1: " + getResources().getResourceEntryName(btn.getId()));
                            nSquare_1 = btn;
                        } else if (nRemoved == nSquare_2) {
                            System.out.println("N2: " + getResources().getResourceEntryName(btn.getId()));
                            nSquare_2 = btn;
                        }
                        // A piece is no longer removed
                        nRemoved = null;
                        System.out.println("nR: " + nRemoved);
                    }
                }
            }
        } else {
            if (buttonsClicked.contains(btn)) {
                // The button is already added: removing the button
                System.out.println("Removing button");
                // Finding the index
                int index = 0;
                for (int i = 0; i < buttonsClicked.size(); i++) {
                    if (btn == buttonsClicked.get(i)) {
                        index = i;
                        i = buttonsClicked.size();
                    }
                }
                System.out.println("form index " + index);
                // Removing the color of the button
                if (playersTurn == 1 && lShape_1.contains(btn)) {
                    ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, color_L1_light));
                } else if (playersTurn == 2 && lShape_2.contains(btn)) {
                    ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, color_L2_light));
                } else {
                    ViewCompat.setBackgroundTintList(btn, ContextCompat.getColorStateList(this, android.R.color.white));
                }
                // Removing the button from the list
                buttonsClicked.remove(index);
            } else {
                // The button is not added: adding the button
                if (nSquare_1 != btn && nSquare_2 != btn) {
                    if (playersTurn == 1 && !lShape_2.contains(btn)) {
                        addBtn(btn);
                    } else if (playersTurn == 2 && !lShape_1.contains(btn)) {
                        addBtn(btn);
                    }
                }
            }
        }
    }

    // Finds the (x,y)-position of a button
    private String fromBtnIdToPos(Button btn) {
        String id = String.valueOf(getResources().getResourceEntryName(btn.getId()));
        char[] id_chars = id.toCharArray();
        String xy = id_chars[6] + "," + id_chars[7];
        return xy;
    }

    private void addBtn(Button b) {
        System.out.println("Adding " + getResources().getResourceEntryName(b.getId()));
        if (buttonsClicked.size() < 4) {
            // Adding the button
            buttonsClicked.add(b);
            // Coloring the button
            if (playersTurn == 1) {
                ViewCompat.setBackgroundTintList(buttonsClicked.get(buttonsClicked.size() - 1), ContextCompat.getColorStateList(this, color_L1_int));
            } else {
                ViewCompat.setBackgroundTintList(buttonsClicked.get(buttonsClicked.size() - 1), ContextCompat.getColorStateList(this, color_L2_int));
            }
        }
    }

    public void doneClicked(View view) {
        System.out.println("moveN: " + moveNPiece);
        System.out.println("turn: " + playersTurn);

        if (buttonsClicked.size() == 4 && newLPosOk(btnListToPosList())) {
            System.out.println("ok");
            if (playersTurn == 1) {
                lShape_1.clear();
                for (Button b : buttonsClicked) {
                    lShape_1.add(b);
                }
            } else {
                lShape_2.clear();
                for (Button b : buttonsClicked) {
                    lShape_2.add(b);
                }
            }
            moveNPiece = true;
            helpText.setText(R.string.moveNeutralPiece);
        } else if (moveNPiece) {
            if (playersTurn == 1) {
                playersTurn = 2;
                whoMoves.setText(colorL2 + " moves");
            } else {
                playersTurn = 1;
                whoMoves.setText(colorL1 + " moves");

            }
            helpText.setText(R.string.moveL);
            if (gameOver()) {
                gameOver = true;
            } else {
                gameOver = false;
            }
            moveNPiece = false;
            nRemoved = null;
            nChanged = null;
            n1_original = nSquare_1;
            n2_original = nSquare_2;
        } else {
            // Returning the board to previous state
            System.out.println("not ok");
            moveNPiece = false;
        }
        buttonsClicked.clear();
        System.out.println(playersTurn + " " + moveNPiece);
        if (playersTurn == 1 && !moveNPiece) {
            colorGrid(color_L1_light, color_L2_int);
        } else if (playersTurn == 2 && !moveNPiece) {
            colorGrid(color_L1_int, color_L2_light);
        } else {
            colorGrid(color_L1_int, color_L2_int);
        }
        if (gameOver) {
            System.out.println("FERDIG");
            if (playersTurn == 1) {
                winner = "Player 2";
            } else {
                winner = "Player 1";
            }
            Intent intentWin = new Intent(this, GameOverActivity.class);
            intentWin.putExtra(INFO_COLOR_MODE, colorMode);
            intentWin.putExtra(INFO_COLOR_L1, colorL1);
            intentWin.putExtra(INFO_COLOR_L2, colorL2);
            intentWin.putExtra(INFO_WINNER, winner);
            intentWin.putExtra(INFO_WINNUMBER_P1, winNr1);
            intentWin.putExtra(INFO_WINNUMBER_P2, winNr2);
            intentWin.putExtra(INFO_WINNUMBER_YOU, winNrYou);
            intentWin.putExtra(INFO_WINNUMBER_AI, winNrAI);
            startActivity(intentWin);
        } else {
            System.out.println("ikke ferdig");
        }
    }

    private boolean gameOver() {
        ArrayList<String> pos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pos.add("");
        }
        ArrayList<String> availableSquares = findAvailableSquares();
        Collections.sort(availableSquares);

        for (String s : availableSquares) { // Ser ok ut
            System.out.println(s);
        }

        for (String s1 : availableSquares) {
            pos.set(0, s1);
            for (String s2 : availableSquares) {
                if (!pos.contains(s2)) {
                    pos.set(1, s2);
                    for (String s3 : availableSquares) {
                        if (!pos.contains(s3)) {
                            pos.set(2, s3);
                            for (String s4 : availableSquares) {
                                if (!pos.contains(s4)) {
                                    pos.set(3, s4);
                                    if (squaresOk(pos)) {
                                        for (String p : pos) {
                                            System.out.print(p + " ");
                                        }
                                        System.out.println();
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean squaresOk(ArrayList<String> pos) {
        ArrayList<String> positions = (ArrayList<String>) pos.clone();
        Collections.sort(positions);
        if (!squaresAreNotLShape(positions)) {
            System.out.println("Squares are L-shape");
            if (posNotTaken(positions)) {
                System.out.println("And not occupied");
                return true;
            }
        }
        return false;
    }

    private boolean posNotTaken(ArrayList<String> positions) {
        ArrayList<String> lShape_1_pos = new ArrayList<>();
        ArrayList<String> lShape_2_pos = new ArrayList<>();
        if (playersTurn == 1) {
            for (Button b : lShape_1) {
                lShape_1_pos.add(fromBtnIdToPos(b));
            }
            Collections.sort(lShape_1_pos);
            for (int i = 0; i<4; i++) {
                if (!lShape_1_pos.get(i).equals(positions.get(i))) {
                    return true;
                }
            }
        } else {
            for (Button b : lShape_2) {
                lShape_2_pos.add(fromBtnIdToPos(b));
            }
            Collections.sort(lShape_2_pos);
            for (int i = 0; i<4; i++) {
                if (!lShape_2_pos.get(i).equals(positions.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> findAvailableSquares() {
        ArrayList<String> occupiedSqares = findOccupiedSquares(); // Disse er riktige
        ArrayList<String> availableSquares = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                String newSquare = x + "," + y;
                if (!occupiedSqares.contains(newSquare)) {
                    availableSquares.add(newSquare);
                }
            }
        }
        return availableSquares;
    }

    private ArrayList<String> findOccupiedSquares() {
        ArrayList<String> occupiedSquares = new ArrayList<>();
        occupiedSquares.add(fromBtnIdToPos(nSquare_1));
        occupiedSquares.add(fromBtnIdToPos(nSquare_2));
        if (playersTurn == 1) {
            for (Button btn : lShape_2) {
                occupiedSquares.add(fromBtnIdToPos(btn));
            }
        } else {
            for (Button btn : lShape_1) {
                occupiedSquares.add(fromBtnIdToPos(btn));
            }
        }
        Collections.sort(occupiedSquares);
        System.out.println("Antall opptatte ruter er 6: " + (occupiedSquares.size() == 6));
        for (String o : occupiedSquares) {
            System.out.println(o);
        }
        System.out.println();
        return occupiedSquares;
    }

    public boolean newLPosOk(ArrayList<String> squaresClicked) {
        System.out.println("Validating position choice");
        Collections.sort(squaresClicked);
        int likenessL1 = 0;
        int likenessL2 = 0;
        if (!squaresAreNotLShape(squaresClicked)) {
            System.out.println("Squares are L-shape");
            for (Button b : buttonsClicked) {
                if (lShape_1.contains(b)) {
                    likenessL1++;
                    System.out.println("liknessL1: " + likenessL1);
                } else if (lShape_2.contains(b)){
                    likenessL2++;
                    System.out.println("liknessL2: " + likenessL2);
                }
            }
            if (playersTurn == 1) {
                if (likenessL1 < 4 && likenessL2 < 1 && !buttonsClicked.contains(nSquare_1) && !buttonsClicked.contains(nSquare_2)) {
                    System.out.println("moveN: " + moveNPiece);
                    System.out.println("turn: " + playersTurn);
                    return true;
                }
            } else {
                if (likenessL1 < 1 && likenessL2 < 4 && !buttonsClicked.contains(nSquare_1) && !buttonsClicked.contains(nSquare_2)) {
                    System.out.println("moveN: " + moveNPiece);
                    System.out.println("turn: " + playersTurn);
                    return true;
                }
            }
        } else {
            System.out.println("Squares are not L-shape");
            System.out.println("moveN: " + moveNPiece);
            System.out.println("turn: " + playersTurn);
        }
        return false;
    }

    private ArrayList<String> btnListToPosList() {
        ArrayList<String> squaresClicked = new ArrayList<>();
        for (Button btn : buttonsClicked) {
            System.out.println(getResources().getResourceEntryName(btn.getId()));
            squaresClicked.add(fromBtnIdToPos(btn));
        }

        for (String s : squaresClicked) {
            System.out.println("(" + s + ")");
        }
        return squaresClicked;
    }

    private boolean squaresAreNotLShape(ArrayList<String> squares) {
        //System.out.println("Sjekker om L-form");
        boolean squaresNotL = true;
        boolean threeSquaresInALine = false;
        boolean squaresHasCornerShape = false;
        ArrayList<String> x_ruteNaboer = new ArrayList<>();
        ArrayList<String> y_ruteNaboer = new ArrayList<>();
        for (String i : squares) {
            //System.out.println("(" + i + ")");
        }
        //System.out.println("Starter sjekk");
        //System.out.println("Første sjekk");
        for (String square1 : squares) {
            //System.out.println("sq1: " + square1);
            String[] koordinater1 = square1.split(",");
            int square1_x = Integer.parseInt(koordinater1[0]);
            int square1_y = Integer.parseInt(koordinater1[1]);
            for (String square2 : squares) {
                //System.out.println("sq2: " + square2);
                String[] koordinater2 = square2.split(",");
                int square2_x = Integer.parseInt(koordinater2[0]);
                int square2_y = Integer.parseInt(koordinater2[1]);
                // Det er en L hvis de tre rutene med lik x/y har y/x som vokser eller synker
                //System.out.println("Ruter: (" + square1 + ") (" + square2 + ")");
                if (!square1.equals(square2)) {
                    //System.out.println((square1_x+1) + " == " + (square2_x) + " && " + square1_y + " == " + square2_y);
                    if (square1_x+1 == square2_x && square1_y == square2_y) {
                        if (!x_ruteNaboer.contains(square1)) {
                            x_ruteNaboer.add(square1);
                            //System.out.println(square1 + " lagt til");
                        }
                        if (!x_ruteNaboer.contains(square2)) {
                            x_ruteNaboer.add(square2);
                            //System.out.println(square2 + " lagt til");
                        }
                    }
                    //System.out.println((square1_y+1) + " == " + (square2_y) + " && " + square1_x + " == " + square2_x);
                    if (square1_y+1 == square2_y && square1_x == square2_x) {
                        if (!y_ruteNaboer.contains(square1)) {
                            y_ruteNaboer.add(square1);
                            //System.out.println(square1 + " lagt til");
                        }
                        if (!y_ruteNaboer.contains(square2)) {
                            y_ruteNaboer.add(square2);
                            //System.out.println(square2 + " lagt til");
                        }
                    }
                }
            }
        }

        //System.out.println("Andre sjekk");
        System.out.println(x_ruteNaboer.size() + " " + y_ruteNaboer.size());
        // hvis en av enderutene er ved siden av en rute på en annen rad
        if (x_ruteNaboer.size() == 2 && y_ruteNaboer.size() == 3) {
            threeSquaresInALine = true;
            for (String square : x_ruteNaboer) {
                //System.out.println(y_ruteNaboer.get(0) + " " + square + " " + y_ruteNaboer.get(2));
                if (y_ruteNaboer.get(0).split(",")[1].equals(square.split(",")[1]) || y_ruteNaboer.get(2).split(",")[1].equals(square.split(",")[1])) {
                    squaresHasCornerShape = true;
                }
            }

        }
        if (y_ruteNaboer.size() == 2 && x_ruteNaboer.size() == 3) {
            threeSquaresInALine = true;
            for (String square : y_ruteNaboer) {
                System.out.println(x_ruteNaboer.get(0) + " " + square + " " + x_ruteNaboer.get(2));
                if (x_ruteNaboer.get(0).split(",")[0].equals(square.split(",")[0]) || x_ruteNaboer.get(2).split(",")[0].equals(square.split(",")[0])) {
                    squaresHasCornerShape = true;
                }
            }
        }
        if (threeSquaresInALine && squaresHasCornerShape) {
            squaresNotL = false;
        }
        return squaresNotL;
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
}