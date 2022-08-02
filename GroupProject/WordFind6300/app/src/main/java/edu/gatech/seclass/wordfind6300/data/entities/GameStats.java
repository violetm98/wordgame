package edu.gatech.seclass.wordfind6300.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Anqi Wang on 2020-02-23.
 */

@Entity (tableName = "game_stats")
public class GameStats {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int finalGameScore;
    private int numReset;
    private int numWords;
    private int boardSize;
    private int numMinutes;
    private String highestScoringWord;

    public GameStats(int finalGameScore, int numReset, int numWords, int boardSize, int numMinutes, String highestScoringWord) {
        this.finalGameScore = finalGameScore;
        this.numReset = numReset;
        this.numWords = numWords;
        this.boardSize = boardSize;
        this.numMinutes = numMinutes;
        this.highestScoringWord = highestScoringWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFinalGameScore() {
        return finalGameScore;
    }

    public int getId() {
        return id;
    }

    public int getNumReset() {
        return numReset;
    }

    public int getNumWords() {
        return numWords;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumMinutes() {
        return numMinutes;
    }

    public String getHighestScoringWord() {
        return highestScoringWord;
    }
}
