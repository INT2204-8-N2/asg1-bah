/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Dictionary {
    private ArrayList<Word> listWord = new ArrayList<Word>();
    public void addWord(String target, String explain){
        Word word = new Word(target, explain);
        listWord.add(word);
    }
    public int size(){
        return listWord.size();
    }
    public Word getWord(int i){
        return listWord.get(i);
    }
    public void remove(int i){
        listWord.remove(i);
    }
}
