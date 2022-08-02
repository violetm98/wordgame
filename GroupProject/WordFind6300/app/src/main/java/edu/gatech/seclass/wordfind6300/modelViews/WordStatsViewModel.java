package edu.gatech.seclass.wordfind6300.modelViews;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.gatech.seclass.wordfind6300.data.entities.WordStats;
import edu.gatech.seclass.wordfind6300.data.repositories.StatsRepository;

/**
 * Created by Anqi Wang on 2020-02-27.
 */
public class WordStatsViewModel extends AndroidViewModel {

    private StatsRepository repository;
    private LiveData<List<WordStats>> allWordStats;

    public WordStatsViewModel(@NonNull Application application) {
        super(application);
        repository = new StatsRepository(application);
        allWordStats = repository.getAllWordStats();
    }


    public void insertWordStats(WordStats wordStats){
        repository.insertWordStats(wordStats);
    }

    public LiveData<List<WordStats>> getAllWordStats(){
        return allWordStats;
    }
}
