package edu.gatech.seclass.wordfind6300;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import edu.gatech.seclass.wordfind6300.modelViews.WordStatsViewModel;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;

public class WordStatsActivity extends AppCompatActivity {

    private static String[] headers = {"Word", "Frequency"};
    private static String [][] wordStats ;
    private ImageButton HomeButton;
    private ImageButton StatsButton;


    private static WordStatsViewModel wordStatsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // go back to parent: stats
//        getSupportActionBar().setTitle("Stats Home");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StatsButton = (ImageButton) findViewById(R.id.StatsButton);
        StatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                returnStats();
            }

        });

        //home listener
        HomeButton = (ImageButton) findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                returnHome();
            }

        });
        final TableView<String[]> tableView = findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(Color.parseColor("#3599C7"));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));
        tableView.setColumnCount(2);
        wordStatsViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(WordStatsViewModel.class);
        wordStatsViewModel.getAllWordStats().observe(this, new Observer<List<WordStats>>() {
            @Override
            public void onChanged(@Nullable List<WordStats> wordStats) {

                if (wordStats != null && wordStats.size()>0){
                    setData(wordStats);
                    tableView.setDataAdapter(new SimpleTableDataAdapter(WordStatsActivity.this, WordStatsActivity.wordStats));
                }
            }
        });

    }

    private void setData(List<WordStats> wordStats){
        WordStatsActivity.wordStats = new String [wordStats.size()][2];
        for (int i = 0; i < wordStats.size(); i++) {
            WordStats wordStat = wordStats.get(i);
            WordStatsActivity.wordStats[i][0] = wordStat.getWord();
            WordStatsActivity.wordStats[i][1] = Integer.toString(wordStat.getFrequency());
        }
    }

    public void returnHome(){
        Intent intent =new Intent(this, AppManager.class); //sender, receiver
        startActivity(intent);
    }
    
    public void returnStats(){
        Intent intent =new Intent(this, Stats.class); //sender, receiver
        startActivity(intent);
    }

}
