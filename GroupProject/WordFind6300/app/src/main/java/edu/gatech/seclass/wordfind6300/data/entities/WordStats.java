package edu.gatech.seclass.wordfind6300.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Anqi Wang on 2020-02-23.
 */

@Entity (tableName = "word_stats")
public class WordStats {

    @NonNull
    @PrimaryKey
    private String word;
    private int frequency;

    public WordStats(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }
}
