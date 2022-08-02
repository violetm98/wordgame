package edu.gatech.seclass.wordfind6300.data.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.gatech.seclass.wordfind6300.data.daos.GameStatsDao;
import edu.gatech.seclass.wordfind6300.data.daos.WordStatsDao;
import edu.gatech.seclass.wordfind6300.data.entities.GameStats;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;


/**
 * Created by Anqi Wang on 2020-02-23.
 */

@Database(entities = {GameStats.class, WordStats.class}, version = 1, exportSchema = false)
public abstract class StatsDatabase extends RoomDatabase {

    private static StatsDatabase instance;

    public abstract GameStatsDao gameStatsDao();

    public  abstract WordStatsDao wordStatsDao();

    public static synchronized StatsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StatsDatabase.class,  "stats_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
