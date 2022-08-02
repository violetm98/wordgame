package edu.gatech.seclass.wordfind6300.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.databases.StatsDatabase;
import edu.gatech.seclass.wordfind6300.data.daos.GameStatsDao;
import edu.gatech.seclass.wordfind6300.data.daos.WordStatsDao;
import edu.gatech.seclass.wordfind6300.data.entities.GameStats;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;


/**
 * Created by Anqi Wang on 2020-02-24.
 */
public class StatsRepository {
    private WordStatsDao wordStatsDao;
    private GameStatsDao gameStatsDao;
    private LiveData<List<GameStats>> allGameStats;
    private LiveData<List<WordStats>> allWordStats;

    public StatsRepository(Application application) {
        StatsDatabase database = StatsDatabase.getInstance(application);
        wordStatsDao = database.wordStatsDao();
        gameStatsDao = database.gameStatsDao();
        allGameStats = gameStatsDao.getGameStats();
        allWordStats = wordStatsDao.getWordStats();
    }

    public void insertGameStats(GameStats gameStats){
        new InsertGameStatsAsyncTask(gameStatsDao).execute(gameStats);

    }

    public void insertWordStats(WordStats wordStats){
        new InsertWordStatsAsyncTask(wordStatsDao).execute(wordStats);

    }

    public void updateWordStats(WordStats wordStats){
        new UpdateWordStatsAsyncTask(wordStatsDao).execute(wordStats);

    }

    public LiveData<List<GameStats>> getAllGameStats() {
        return allGameStats;
    }

    public LiveData<List<WordStats>> getAllWordStats() {
        return allWordStats;
    }

    private static class InsertGameStatsAsyncTask extends AsyncTask<GameStats, Void, Void>  {

        private GameStatsDao gameStatsDao;

        private  InsertGameStatsAsyncTask(GameStatsDao gameStatsDao){
            this.gameStatsDao = gameStatsDao;
        }

        @Override
        protected Void doInBackground(GameStats... gameStats) {
            gameStatsDao.insertGameStats(gameStats[0]);
            return null;
        }
    }

    private static class InsertWordStatsAsyncTask extends AsyncTask<WordStats, Void, Void> {

        private WordStatsDao wordStatsDao;
        private InsertWordStatsAsyncTask(WordStatsDao wordStatsDao){
            this.wordStatsDao = wordStatsDao;
        }


        @Override
        protected Void doInBackground(WordStats... wordStats) {
            wordStatsDao.insertWordStats(wordStats[0]);
            return null;
        }
    }

    private static class UpdateWordStatsAsyncTask extends AsyncTask<WordStats, Void, Void> {

        private WordStatsDao wordStatsDao;
        private UpdateWordStatsAsyncTask(WordStatsDao wordStatsDao){
            this.wordStatsDao = wordStatsDao;
        }


        @Override
        protected Void doInBackground(WordStats... wordStats) {
            wordStatsDao.updateWordStats(wordStats[0]);
            return null;
        }
    }
}
