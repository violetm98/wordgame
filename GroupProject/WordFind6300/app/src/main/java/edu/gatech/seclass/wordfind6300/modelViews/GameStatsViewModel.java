package edu.gatech.seclass.wordfind6300.modelViews;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import edu.gatech.seclass.wordfind6300.data.entities.GameStats;
import edu.gatech.seclass.wordfind6300.data.repositories.StatsRepository;


/**
 * Created by Anqi Wang on 2020-02-24.
 */
public class GameStatsViewModel extends AndroidViewModel {

    private StatsRepository repository;
    private LiveData<List<GameStats>> allGameStats;

    public GameStatsViewModel(@NonNull Application application) {
        super(application);
        repository = new StatsRepository(application);
        allGameStats = repository.getAllGameStats();
    }

    public LiveData<List<GameStats>> getAllGameStats(){
        return allGameStats;
    }

}
