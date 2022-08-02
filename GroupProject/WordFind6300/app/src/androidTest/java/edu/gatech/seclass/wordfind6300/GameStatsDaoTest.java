package edu.gatech.seclass.wordfind6300;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.daos.GameStatsDao;
import edu.gatech.seclass.wordfind6300.data.databases.StatsDatabase;
import edu.gatech.seclass.wordfind6300.data.entities.GameStats;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Anqi Wang on 2020-03-03.
 *
 */

@RunWith(AndroidJUnit4.class)
public class GameStatsDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();




    private GameStatsDao gameStatsDao;
    private StatsDatabase statsDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        statsDatabase = Room.inMemoryDatabaseBuilder(context, StatsDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        gameStatsDao = statsDatabase.gameStatsDao();
    }
    @After
    public void closeDb() {
        statsDatabase.close();
    }

    @Test
    public void getAllWords() throws Exception {
        GameStats gameStats1 = new GameStats(8,2,3,4,
                3,"bbbbb");
        WordStats wordStats2 = new WordStats("bbb", 2);
        GameStats gameStats2 = new GameStats(10,3,3,4,
                3,"aaaaaaa");

        gameStatsDao.insertGameStats(gameStats1);
        gameStatsDao.insertGameStats(gameStats2);
        List<GameStats> allGames = LiveDataTestUtil.getValue(gameStatsDao.getGameStats());
        assertEquals(allGames.get(0).getFinalGameScore(), 10);
        assertEquals(allGames.get(1).getFinalGameScore(),8);

    }
}