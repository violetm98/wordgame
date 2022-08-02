package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Stats extends AppCompatActivity {

    private Button GameStatsbutton;
    private Button WordStatsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // go back to parent: app manager
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //wgame stats listener
        GameStatsbutton = (Button) findViewById(R.id.GameStatsbutton);
        GameStatsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openGameStats();
            }

        });

        //word stats listener
        WordStatsbutton = (Button) findViewById(R.id.WordStatsbutton);
        WordStatsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openWordStats();
            }

        });

    }


    public void openGameStats(){
        Intent intent =new Intent(this, GameStatsActivity.class); //sender, receiver
        startActivity(intent);
    }


    public void openWordStats(){
        Intent intent =new Intent(this, WordStatsActivity.class); //sender, receiver
        startActivity(intent);
    }

}
