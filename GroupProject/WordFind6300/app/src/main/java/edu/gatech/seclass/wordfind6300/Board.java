package edu.gatech.seclass.wordfind6300;


import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.distribution.EnumeratedDistribution;

public class Board {
    int boardsize;
    Map<String, Integer> letterWeight;
    public String[][] boardLetters;

    Board(Map letterWeight){
        this.boardsize = 4;
        this.letterWeight = letterWeight;
        this.boardLetters = null;
    }

    Board(int boardsize, Map letterWeight){
        this.boardsize = boardsize;
        this.letterWeight = letterWeight;
        this.boardLetters = null;
    }

    //This method will generate the board letters
    String[] testBoard(){
        String[] result = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m",
                "n","o","p"};
        return result;
    }
    String[][] generateBoard(int bsize, Map<String, Integer> letterWeight){

        int boardsize= bsize*bsize;
        boardLetters = new String[bsize][bsize];
        double totalWeight = 0.0;
        String[] myArray = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m",
                "n","o","p","q","r","s","t","u","v","w","x","y","z"};
        //The following part needs to be read from the settings---start
        for(String s:letterWeight.keySet()){
            if(s.equals("a") | s.equals("e") | s.equals("i") | s.equals("o")| s.equals("u")){
                totalWeight += letterWeight.get(s)*4;
            } else {
                totalWeight+= letterWeight.get(s);
            }
        }
        //end

        Map<String, Double> finalWeight = new HashMap<>();
        for(String s:letterWeight.keySet()){
            finalWeight.put(s, letterWeight.get(s)/totalWeight);
        }


        final List<Pair<String, Double>> itemWeights = new ArrayList();
        for (String i : finalWeight.keySet()) {
            boolean add = itemWeights.add(new Pair(i, finalWeight.get(i)));
        }

        Object[] selectedItem = new EnumeratedDistribution<>(itemWeights).sample(boardsize);
        for(int i =0; i < selectedItem.length; i++){
            int rowidx = i/bsize;
            int colidx = i%bsize;
            boardLetters[rowidx][colidx] = selectedItem[i].toString();
        }
        return boardLetters;
    }
}
