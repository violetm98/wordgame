package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import edu.gatech.seclass.wordfind6300.modelViews.GameStatsViewModel;
import edu.gatech.seclass.wordfind6300.data.entities.GameStats;

public class GameStatsActivity extends AppCompatActivity {

    private static String[] headers = {"Rank","Score","Resets","Words"};
    private static String [][] gameStats ;
    private ImageButton HomeButton;
    private ImageButton StatsButton;


    private static GameStatsViewModel gameStatsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);
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
        tableView.setColumnCount(4);
        tableView.addDataClickListener(new GameStatsClickListener());

        gameStatsViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(GameStatsViewModel.class);
        gameStatsViewModel.getAllGameStats().observe(this, new Observer<List<GameStats>>() {
            @Override
            public void onChanged(@Nullable  List<GameStats> gameStats) {

                if (gameStats != null && gameStats.size()>0){
                    setData(gameStats);
                    tableView.setDataAdapter(new SimpleTableDataAdapter(GameStatsActivity.this, GameStatsActivity.gameStats));
                }
            }
        });

    }

    private void setData(List<GameStats> gameStats){
        GameStatsActivity.gameStats = new String [gameStats.size()][4];
        for (int i = 0; i < gameStats.size(); i++) {
            GameStats gameStat = gameStats.get(i);
            GameStatsActivity.gameStats[i][0] = Integer.toString(i+1);
            GameStatsActivity.gameStats[i][1] = Integer.toString(gameStat.getFinalGameScore());
            GameStatsActivity.gameStats[i][2] = Integer.toString(gameStat.getNumReset());
            GameStatsActivity.gameStats[i][3] = Integer.toString(gameStat.getNumWords());
        }
    }


    public class GameStatsClickListener implements TableDataClickListener<String[]> {
        @Override
            public void onDataClicked(int rowIndex, String[] chosenRow) {
            GameStats gameStats = gameStatsViewModel.getAllGameStats().getValue().get(rowIndex);
            int boardSize = gameStats.getBoardSize();
            int numOfMinutes = gameStats.getNumMinutes();
            String word = gameStats.getHighestScoringWord();
            String info = "BoardSize: " + boardSize + "       Number of Minutes: " + numOfMinutes + "\nHighest Scoring Word: " + word;
            Snackbar.make(findViewById(R.id.toolbar), info, Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.YELLOW)
                    .setBackgroundTint(Color.GRAY).show();
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
