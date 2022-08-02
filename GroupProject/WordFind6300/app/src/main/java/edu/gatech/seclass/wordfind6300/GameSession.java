package edu.gatech.seclass.wordfind6300;

import android.os.Bundle;
import android.graphics.Color;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TableRow;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import edu.gatech.seclass.wordfind6300.data.entities.GameStats;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;
import edu.gatech.seclass.wordfind6300.modelViews.GameSessionViewModel;

public class GameSession extends AppCompatActivity {
    //start board
    String[] myArray = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m",
            "n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public Board board;
    public Setting setting;
    public int counter;

    public int NumReolls;
    public int NumMinutes;
    public String highestScoringWord;
    public int finalScore = 0;
    public Boolean[][] userInputIndexes;
    public Map<String, Integer> wordFrequency = new HashMap<>();
    public Map<String, Integer> wordScore = new HashMap<>();
    public int highestScore;
    public int letterEntered;
    public String[][] letterArray;
    public int boardSize;
    public String wordEntered;
    public int numWords;
    Map<String, Integer> letterWeight = new HashMap<>();
    public int PreviousEntry = -1;

    public TextView timerTitle;
    public TextView CurrentWord;
    public ImageButton validateButton;
    public ImageButton clearButton;
    public ImageButton rerollButton;
    public ImageButton exitButton;
    public TableLayout ll;
    private GameSessionViewModel gameSessionViewModel;
    List<Button> buttons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_session);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gameSessionViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(GameSessionViewModel.class);
    //Get value from settings
        Intent settingIntent = getIntent();
        String StringboardSize = settingIntent.getStringExtra("boardSizeSetting");
        String Stringwt = settingIntent.getStringExtra("wtSetting");
        String Stringtm = settingIntent.getStringExtra("timerSetting");


        boardSize = Integer.valueOf(StringboardSize);
        NumMinutes = Integer.valueOf(Stringtm);
        String[] weightNumber = Stringwt.split(",");
    //set the timer
        timerTitle = findViewById(R.id.TimerTitle);
        final CountDownTimer timer =
                new CountDownTimer(NumMinutes*1000*60, 1000){
                    public void onTick(long millisUntilFinished){
                        timerTitle.setText(intToTime(NumMinutes*60 - counter));
                        counter++;
                    }
                    public  void onFinish(){
                        PopupWindow();
                    }
                };


        for(int i =0; i< weightNumber.length; i++){
            if(myArray[i].equals("a") | myArray[i].equals("e") | myArray[i].equals("i") | myArray[i].equals("o")| myArray[i].equals("u")){
                letterWeight.put(myArray[i], Integer.valueOf(weightNumber[i])*4);
            } else {
                letterWeight.put(myArray[i], Integer.valueOf(weightNumber[i]));
            }
        }
    //Initialize the board
        final Board board = new Board(boardSize,letterWeight);
        TableLayout ll = (TableLayout) findViewById(R.id.boardLayout);
        NumReolls = 0;
        letterArray = board.generateBoard(boardSize, letterWeight);
        //will change the board size from setting in the future
        addLetters(boardSize, letterArray);
    //Initial Current word as blank
        CurrentWord =(TextView) findViewById(R.id.CurrentWord);
        wordEntered = "";
        CurrentWord.setText(wordEntered);
    //Initial userInputIndexes and selectedIndexes
        letterEntered = 0;
        numWords = 0;
        userInputIndexes = new Boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(userInputIndexes[i], Boolean.FALSE);
        }
    //Initial all score related attributes
        NumReolls = 0;
        highestScoringWord = "";
        finalScore = 0;
        highestScore = 0;
    //Start Timer
        timer.start();
    //set the validate button
        validateButton = (ImageButton) findViewById(R.id.ValidateButton);
        clearButton = (ImageButton) findViewById(R.id.ClearButton);
        rerollButton = (ImageButton) findViewById(R.id.rerollButton);
        exitButton = (ImageButton) findViewById(R.id.exitbutton);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitWord();

            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearWord();
            }
        });

        rerollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reroll(board);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast  ScoreToast = Toast.makeText(getApplicationContext(),"Your Score is " + finalScore, Toast.LENGTH_LONG);
//                View view = ScoreToast.getView();
//                ScoreToast.show();
                timer.cancel();
                PopupWindow();

            }
        });
    }

    private void clearWord() {

        wordScore.put(wordEntered, letterEntered);
        wordEntered = "";
        letterEntered = 0;
        CurrentWord.setText(wordEntered);
        PreviousEntry = -1;
//            //empty the userInputIndxes
        for (int i = 0; i < userInputIndexes.length; i++) {
            for (int j = 0; j < userInputIndexes[0].length; j++){
                if(userInputIndexes[i][j]){
                    Button currentButton = buttons.get(i*boardSize + j);
                    userInputIndexes[i][j] = false;
                    currentButton.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }

    public  String intToTime(int counter){
        String minutePart = Integer.toString(counter/60);
        int secondPart = counter%60;
        String secondStr = Integer.toString(secondPart);
        //if the length the second part is less than 2, than padding 0 in front of it
        if (secondStr.length() < 2){
            secondStr = "0" + secondStr;
        }
        final String s = minutePart + ":" + secondStr;
        return s;

    }
    public static class tuple {
        int x;
        int y;
        tuple(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public  Boolean ValidateWords(Boolean[][] userInputIndexes, int letterEntered) {
        Queue<tuple> currentQueue = new LinkedList<>();
        int rowSize = userInputIndexes.length;
        int colSize = userInputIndexes[0].length;
        int totalVisits = 0;
        String totalVisitsIndex ="user Input indexes";
        int allUserInput = 0;
        //if the length of the input words has a length less than 2
        if(letterEntered <2){
            Toast  InvalidInput = Toast.makeText(getApplicationContext(), "The word length is less than 2.", Toast.LENGTH_LONG);
            View view = InvalidInput.getView();
            view.setBackgroundColor(Color.RED);
            InvalidInput.show();
            return  false;
        }
        Boolean[][] visitIndexes = new Boolean[rowSize][colSize];
        for (int i = 0; i < userInputIndexes.length; i++) {
            Arrays.fill(visitIndexes[i], Boolean.FALSE);
        }

        if(wordScore.containsKey(wordEntered)){
            Toast  InvalidInput = Toast.makeText(getApplicationContext(), "This word has been entered.", Toast.LENGTH_LONG);
            View view = InvalidInput.getView();
            view.setBackgroundColor(Color.RED);
            InvalidInput.show();
            return false;
        }
        return true;
    }

    public void reroll(Board board){
        finalScore -= 5;
        NumReolls ++;
        letterArray = board.generateBoard(boardSize, letterWeight);
//        board.testBoard();
        //will change the board size from setting in the future

        addLetters(boardSize, letterArray);
    }
    public void exit(){

    }
    public Boolean validateWord(){
        return true;

    }
    public void addLetters(final int boardSize, String[][] letterArray) {

        //ll.setShrinkAllColumns(true);
        //ll.setStretchAllColumns(true);
        TableLayout ll = (TableLayout) findViewById(R.id.boardLayout);
        ll.removeAllViews();
        int tableHeight = ll.getHeight();
        int tableWidth = ll.getWidth();
        int cellCounter = 1;
        for (int i = 0; i < boardSize; i++) {
            TableRow row = new TableRow(this);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TabLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT, 1f);
            row.setLayoutParams(lp);
            for (int j = 0; j < boardSize; j++) {
                Button cell = new Button(this);
                final String letter = letterArray[i][j];
                buttons.add(cell);
                TableRow.LayoutParams buttonParams = new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT, 1f);
                cell.setLayoutParams(buttonParams);
                if(letter.equalsIgnoreCase("Q")){
                    cell.setText("Qu");
                } else {
                    cell.setText(letter.toUpperCase());
                }
                cell.setBackgroundColor(Color.WHITE);
                cell.setTransformationMethod(null);
                cellCounter++;
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object current_index = v.getTag();
                        int currentIndex = Integer.valueOf(current_index.toString());
                        int rowidx = currentIndex/boardSize;
                        int colidx = currentIndex%boardSize;
                        if(userInputIndexes[currentIndex/boardSize][currentIndex%boardSize]){
                            Toast  InvalidInput = Toast.makeText(getApplicationContext(), "The letter is already entered!", Toast.LENGTH_LONG);
                            View view = InvalidInput.getView();
                            view.setBackgroundColor(Color.RED);
                            InvalidInput.show();
                        } else if ((PreviousEntry == -1) |
                                ((Math.abs(rowidx - PreviousEntry/boardSize) <= 1) &
                                        (Math.abs(colidx - PreviousEntry%boardSize) <= 1))){
                            Button b = (Button)v;
                            b.setBackgroundColor(Color.YELLOW);
                            letterEntered++;
                            wordEntered += b.getText().toString();

                            CurrentWord.setText(wordEntered);
                            //update the userInputIndexes and letterEntered
                            userInputIndexes[currentIndex/boardSize][currentIndex%boardSize]= true;
                            PreviousEntry = currentIndex;
                    } else {
                            Toast  InvalidInput = Toast.makeText(getApplicationContext(), "The letter entered is not connected with previous one!", Toast.LENGTH_LONG);
                            View view = InvalidInput.getView();
                            view.setBackgroundColor(Color.RED);
                            InvalidInput.show();
                        }
                    }
                });
                cell.setTag(i*boardSize +j);
                row.addView(cell);
            }

            ll.addView(row);
        }

    }
    public void submitWord(){

        //check whether this is validate word or not
        Boolean ValidateResult = ValidateWords(userInputIndexes, letterEntered);
        if(ValidateResult){
            numWords ++;
            finalScore += wordEntered.length();
            if(letterEntered > highestScore){
                highestScore = letterEntered;
                highestScoringWord = wordEntered;
            }
            if (wordFrequency.containsKey(wordEntered)){
                wordFrequency.put(wordEntered, wordFrequency.get(wordEntered) + 1);
            } else {
                wordFrequency.put(wordEntered, 1);
            }

            wordScore.put(wordEntered, letterEntered);
            wordEntered = "";

            PreviousEntry = -1;
            CurrentWord.setText(wordEntered);
//            //add all words to userInputIndexes
            for (int i=0; i<userInputIndexes.length; i++){
                for(int j=0; j<userInputIndexes[0].length; j++){
                    if(userInputIndexes[i][j]) {
                        Button currentButton = buttons.get(i*boardSize + j);
                        currentButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
//            //empty the userInputIndxes
            for (int i = 0; i < userInputIndexes.length; i++) {
                Arrays.fill(userInputIndexes[i], Boolean.FALSE);
            }


        } else clearWord();
//        else {
//            Toast  InvalidInput = Toast.makeText(getApplicationContext(),"The entered word is invalid", Toast.LENGTH_LONG);
//            View view = InvalidInput.getView();
//            view.setBackgroundColor(Color.RED);
//            InvalidInput.show();
//        }
    }

    public void  PopupWindow(){
        Intent intent =new Intent(this, PopupScore.class); //sender, receiver
        intent.putExtra("finalScore", String.valueOf(finalScore));
        startActivity(intent);
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        GameStats gameStats = new GameStats(finalScore, NumReolls, numWords, boardSize, NumMinutes, highestScoringWord);
        gameSessionViewModel.insertGameStats(gameStats);
        List<WordStats>  allwordStats = gameSessionViewModel.getAllWordStats().getValue();

        for (Map.Entry<String,Integer> entry : wordFrequency.entrySet()) {
            Boolean updateWord = false;
            if(allwordStats != null) {
                for (WordStats wordStats : allwordStats) {
                    if (wordStats.getWord().equals(entry.getKey())) {
                        gameSessionViewModel.updateWordStats(new WordStats(entry.getKey(),
                                entry.getValue() + wordStats.getFrequency()));
                        updateWord = true;
                        break;
                    }
                }
            }
            if (!updateWord){
                gameSessionViewModel.insertWordStats(new WordStats(entry.getKey(),entry.getValue()));
            }
        }
    }
}
