package edu.gatech.seclass.wordfind6300.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.entities.WordStats;

/**
 * Created by Anqi Wang on 2020-02-23.
 */

@Dao
public interface WordStatsDao {

    @Insert
    void insertWordStats(WordStats wordStats);

    @Update
    void updateWordStats(WordStats wordStats);

    @Query("SELECT * FROM word_stats ORDER BY frequency DESC")
    LiveData<List<WordStats>> getWordStats();

}