package edu.gatech.seclass.wordfind6300;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.daos.WordStatsDao;
import edu.gatech.seclass.wordfind6300.data.databases.StatsDatabase;
import edu.gatech.seclass.wordfind6300.data.entities.WordStats;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Anqi Wang on 2020-03-03.
 */

@RunWith(AndroidJUnit4.class)
public class WordStatsDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private WordStatsDao wordStatsDao;
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
        wordStatsDao = statsDatabase.wordStatsDao();
    }
    @After
    public void closeDb() {
        statsDatabase.close();
    }

    @Test
    public void getAllWords() throws Exception {
        WordStats wordStats1 = new WordStats("aaaa", 1);
        WordStats wordStats2 = new WordStats("bbb", 2);
        wordStatsDao.insertWordStats(wordStats1);
        wordStatsDao.insertWordStats(wordStats2);
        List<WordStats> allWords = LiveDataTestUtil.getValue(wordStatsDao.getWordStats());
        assertEquals(allWords.get(0).getWord(), "bbb");
        assertEquals(allWords.get(0).getFrequency(),2);
        assertEquals(allWords.get(1).getWord(), "aaaa");
        assertEquals(allWords.get(1).getFrequency(),1);

    }
    @Test
    public void updateWordStats() throws Exception{
        WordStats wordStats1 = new WordStats("aaaa", 1);
        wordStatsDao.insertWordStats(wordStats1);
        WordStats wordStats2 = new WordStats("aaaa", 3);
        wordStatsDao.updateWordStats(wordStats2);
        List<WordStats> allWords = LiveDataTestUtil.getValue(wordStatsDao.getWordStats());
        assertEquals(allWords.get(0).getFrequency(), 3);

    }

}
