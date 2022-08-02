package edu.gatech.seclass.wordfind6300.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.entities.GameStats;


/**
 * Created by Anqi Wang on 2020-02-23.
 */

@Dao
public interface GameStatsDao {

    @Insert
    void insertGameStats(GameStats gameStats);

    @Query("SELECT * FROM game_stats ORDER BY finalGameScore DESC, numReset ASC, numWords DESC")
    LiveData<List<GameStats> > getGameStats();
}
