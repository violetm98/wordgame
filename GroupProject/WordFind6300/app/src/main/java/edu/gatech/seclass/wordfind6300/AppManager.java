package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import android.view.View;

public class AppManager extends AppCompatActivity {
    //Setting gameSetting = new Setting();
    private ImageButton NewGameButton;
    private ImageButton SettingButton;
    private ImageButton StatsButton;
    //private Spinner spinner; //boardsize
    public static final String boardSizeSetting = "boardSizeSetting";
    public static final String wtSetting = "wtSetting";
    public static final String timerSetting = "timerSetting";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BOARD =  "BOARD";
    public static final String SPINNERWT= "SPINNERWT";
    public static final String TIMER= "TIMER";
    //String boardSize = "4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        saveSetting();
        //New game listener
        NewGameButton = (ImageButton) findViewById(R.id.NewGameButton);
        NewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewGame();

            }

        });

        //setting listener
        SettingButton = (ImageButton) findViewById(R.id.SettingButton);
        SettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSetting();

            }

        });

        //Stats listener
        StatsButton = (ImageButton) findViewById(R.id.StatsButton);
        StatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openStats();
            }

        });
    }
        public void openSetting(){
            Intent intent =new Intent(this, AdjustSetting.class); //sender, receiver
            startActivity(intent);
        }

        public void openNewGame(){
            Intent intent =new Intent(this, GameSession.class); //sender, receivers
            intent.putExtra(boardSizeSetting, loadSetting()[0]);
            intent.putExtra(wtSetting, loadSetting()[1]);
            intent.putExtra(timerSetting, loadSetting()[2]);
            //load data
            startActivity(intent);
        }

        public void openStats() {
            Intent intent = new Intent(this, Stats.class); //sender, receiver
            startActivity(intent);
        }

        public void saveSetting(){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (getSetting()[0]!= null) {
                editor.putString(BOARD, getSetting()[0]);
                editor.putString(SPINNERWT, getSetting()[1]);
                editor.putString(TIMER, getSetting()[2]);
                editor.apply();}

        }

        public String[] loadSetting()
        {
            String[] ans = new String[3];
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            ans[0] = sharedPreferences.getString(BOARD, "4");
            ans[1] = sharedPreferences.getString(SPINNERWT, "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1");
            ans[2] = sharedPreferences.getString(TIMER, "3");

            // returning array of elements
            return ans;
        }

        //pass in boardSize
        public String[] getSetting(){
            Intent intent = getIntent();
            String[] ans = new String[3];
            ans[0]  = intent.getStringExtra(AdjustSetting.boardSizeExtra) ;
            ans[1] = intent.getStringExtra(AdjustSetting.spinnerMapExtra) ;
            ans[2] = intent.getStringExtra(AdjustSetting.timerExtra) ;
            return ans;

        }



}
