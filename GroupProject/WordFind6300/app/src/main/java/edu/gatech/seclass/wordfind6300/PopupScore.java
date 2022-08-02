package edu.gatech.seclass.wordfind6300;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopupScore extends Activity {
    public TextView scoreView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupscore_layout);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Intent settingIntent = getIntent();
        String finalScore = settingIntent.getStringExtra("finalScore");
        scoreView = findViewById(R.id.DisplayScore);
        finalScore = "Congrats! Your final \n score is "+finalScore;
        scoreView.setText(finalScore);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));
    }
}
